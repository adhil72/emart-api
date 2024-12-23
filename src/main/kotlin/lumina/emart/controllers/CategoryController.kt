package lumina.emart.controllers

import lumina.emart.dtos.CreateCategoryDto
import lumina.emart.dtos.UpdateCategoryDto
import lumina.emart.services.CategoryService
import lumina.emart.utils.FetchParams
import org.springframework.web.bind.annotation.*
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
    fun fetchCategories(@ModelAttribute fetchParams: FetchParams) = categoryService.fetchCategories(fetchParams)
}