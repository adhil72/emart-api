package lumina.emart.controllers

import lumina.emart.dtos.CreateProductDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateProductDto
import lumina.emart.services.ProductService
import lumina.emart.utils.FetchParams
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService){

    @PostMapping("/create")
    fun createProduct(@RequestBody createProductDto: CreateProductDto):Response = productService.createProduct(createProductDto)

    @PatchMapping("/update/{id}")
    fun updateProduct(@PathVariable id:String, @RequestBody updateProductDto: UpdateProductDto):Response = productService.updateProduct(id, updateProductDto)

    @DeleteMapping("/delete/{id}")
    fun deleteProduct(@PathVariable id: String):Response = productService.deleteProduct(id)

    @GetMapping("/fetch")
    fun fetchProducts(fetchParams: FetchParams):Response = productService.fetchProducts(fetchParams)
}