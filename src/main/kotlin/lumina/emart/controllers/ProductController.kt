package lumina.emart.controllers

import lumina.emart.dtos.CreateProductDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateProductDto
import lumina.emart.services.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService){

    @PostMapping("/create")
    fun createProduct(@RequestBody createProductDto: CreateProductDto):Response = productService.createProduct(createProductDto)

    @PatchMapping("/update")
    fun updateProduct(@RequestBody updateProductDto: UpdateProductDto):Response = productService.updateProduct(updateProductDto)

    @DeleteMapping("/delete")
    fun deleteProduct(id: String):Response = productService.deleteProduct(id)

    @PostMapping("/fetch")
    fun fetchProducts(page:Int = 1, limit: Int = 10):Response = productService.fetchProducts(page, limit)
}