package lumina.emart.controllers

import lumina.emart.dtos.CreateCategoryDto
import lumina.emart.dtos.UpdateCategoryDto
import lumina.emart.services.CategoryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.swing.SortOrder

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping("/create")
    fun addCategory(@RequestBody createCategoryDto: CreateCategoryDto) = categoryService.createCategory(createCategoryDto)

    @PatchMapping("/update")
    fun updateCategory(@RequestBody updateCategoryDto: UpdateCategoryDto) = categoryService.updateCategory(updateCategoryDto)

    @DeleteMapping("/delete")
    fun deleteCategory(id: String) = categoryService.deleteCategory(id)

    @GetMapping("/fetch")
    fun fetchCategories(page:Int=1, limit: Int=10, sortKey:String?=null, sortOrder:String="asc") = categoryService.fetchCategories(page, limit, sortKey, sortOrder)
}