package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.dtos.MenuProductDto;
import com.igrowker.donatello.exceptions.ForbiddenException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.MenuMapper;
import com.igrowker.donatello.models.Menu;
import com.igrowker.donatello.models.MenuProduct;
import com.igrowker.donatello.models.MenuProductPK;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.repositories.IMenuProductRepository;
import com.igrowker.donatello.repositories.IMenuRepository;
import com.igrowker.donatello.repositories.IProductRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IMenuService;
import com.igrowker.donatello.validators.IMenuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IMenuProductRepository menuProductRepository;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IMenuValidator menuValidator;

    @Override
    public MenuDto save(HttpHeaders headers, MenuDto menuDto) {
        // Validar los campos del menu
        menuValidator.validate(menuDto);

        // Pasar el idUser desde el token obtenido en el headers
        menuDto.setUserId(authService.getLoguedUser(headers).getId());
        Menu menu = menuMapper.toMenu(menuDto);
        menu.getMenuProducts().forEach(menuProduct -> {
            // Obtener el ID del producto desde el DTO
            Integer productId = menuProduct.getId().getIdProduct();

            // Buscar el producto por su ID
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));
            //todo inicialmente no habra ecomerce! =>  Actualizar el stock del producto
            //todo inicialmente no habra ecomerce! =>  product.setStock(product.getStock() - menuProduct.getAmount());
            //todo inicialmente no habra ecomerce! =>  productRepository.save(product);

            // Asignar el producto y el menú a MenuProduct
            menuProduct.setProduct(product);
            menuProduct.setMenu(menu);

        });
        return menuMapper.toMenuDto(menuRepository.save(menu));
    }

    @Override
    public List<MenuDto> getMenus(HttpHeaders headers) {
        Integer userId = authService.getLoguedUser(headers).getId();
        return menuMapper.toMenusDto(menuRepository.findByIdUser(userId));
    }

    @Override
    public MenuDto update(HttpHeaders headers,Integer id, MenuDto menuDto) {
        // Validar los campos del menu
        menuDto.setId(id);
        menuValidator.validate(menuDto);

        // Verificar que el menú a actualizar existe
        Menu existsMenu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));

        // Verificar que el usuario sea el mismo que creo el menu
        Integer userId = authService.getLoguedUser(headers).getId();
        if(! existsMenu.getCustomUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot update someone else's menu.");
        }

        // Crear un mapa para los productos existentes en el menú
        Map<Integer, MenuProduct> existsMenuProductsMap = existsMenu.getMenuProducts().stream()
                .collect(Collectors.toMap(mp -> mp.getProduct().getId(), mp -> mp));

        // Crear un mapa para los productos en el DTO
        Map<Integer, MenuProductDto> menuProductDtoMap = menuDto.getMenuProductDto().stream()
                .collect(Collectors.toMap(MenuProductDto::getProductId, mp -> mp));

        // Identificar y procesar productos eliminados
        List<MenuProduct> productsToRemove = new ArrayList<>();
        for (MenuProduct menuProduct : existsMenu.getMenuProducts()) {
            Integer productId = menuProduct.getProduct().getId();
            if (!menuProductDtoMap.containsKey(productId)) { // todo se refiere a un producto que estaba en el menu pero se va a eliminar del menu, por eso se descuenta del stock..
                // Ajustar el stock del producto eliminado
                Product product = menuProduct.getProduct();
                //todo inicialmente no habra ecomerce! =>  product.setStock(product.getStock() + menuProduct.getAmount());
                productRepository.save(product);
                productsToRemove.add(menuProduct); // Marcar el producto para eliminación
            }
        }
        // Eliminar productos marcados del menú
        existsMenu.getMenuProducts().removeAll(productsToRemove);

        // Eliminar productos del menu de la base de datos
        for (MenuProduct menuProduct : productsToRemove) {
            menuProductRepository.delete(menuProduct);
        }

        // Actualizar los productos del menú
        for (MenuProductDto menuProductDto : menuDto.getMenuProductDto()) {
            Integer productId = menuProductDto.getProductId();

            // Buscar el producto por su ID
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

            // Verificar si el producto ya existe en el menú
            MenuProduct existsMenuProduct = existsMenuProductsMap.get(productId);

            if (existsMenuProduct != null) {
                // Actualizar el stock solo si la cantidad es diferente
                int oldAmount = existsMenuProduct.getAmount();
                int newAmount = menuProductDto.getAmount();

                if (oldAmount != newAmount) {
                    //todo inicialmente no habra ecomerce! =>  product.setStock(product.getStock() + oldAmount - newAmount);
                    //todo inicialmente no habra ecomerce! =>  productRepository.save(product);
                }

                // Actualizar la cantidad del producto en el menú
                existsMenuProduct.setAmount(newAmount);
            } else {
                // Crear y asignar la clave primaria compuesta
                MenuProductPK menuProductPK = new MenuProductPK();
                menuProductPK.setIdMenu(existsMenu.getId());
                menuProductPK.setIdProduct(product.getId());

                // Crear y asignar el producto y el menú a MenuProduct
                MenuProduct newMenuProduct = new MenuProduct();
                newMenuProduct.setId(menuProductPK);
                newMenuProduct.setProduct(product);
                newMenuProduct.setMenu(existsMenu);
                newMenuProduct.setAmount(menuProductDto.getAmount());

                // Ajustar el stock del producto
                product.setStock(product.getStock() - newMenuProduct.getAmount());
                productRepository.save(product);

                existsMenu.getMenuProducts().add(newMenuProduct);
            }
        }

        // Usar el mapper para actualizar el menú
         menuMapper.updateMenu(existsMenu, menuDto);

        // Guardar el menú actualizado
        return menuMapper.toMenuDto(menuRepository.save(existsMenu));
    }

    @Override
    public MenuDto delete(HttpHeaders headers, Integer id) {
        // Encontrar el menú por ID
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));

        // Verificar que el usuario sea el mismo que creó el menú
        Integer userId = authService.getLoguedUser(headers).getId();
        if (!menu.getCustomUser().getId().equals(userId)) {
            throw new ForbiddenException("The user cannot delete someone else's menu.");
        }

        // Crear un DTO del menú antes de eliminarlo
        MenuDto menuDto = menuMapper.toMenuDto(menu);

        // Ajustar el stock de los productos en el menú
        for (MenuProduct menuProduct : menu.getMenuProducts()) {
            //todo inicialmente no habra ecomerce! =>  Product product = menuProduct.getProduct();
            //todo inicialmente no habra ecomerce! =>  product.setStock(product.getStock() + menuProduct.getAmount());
            //todo inicialmente no habra ecomerce! =>  productRepository.save(product);
        }

        // Eliminar los productos del menú
        menuProductRepository.deleteAll(menu.getMenuProducts());

        // Eliminar el menú
        menuRepository.delete(menu);

        return menuDto;
    }

}