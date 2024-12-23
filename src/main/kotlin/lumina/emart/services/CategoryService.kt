package lumina.emart.services

import lumina.emart.dtos.CreateCategoryDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateCategoryDto
import lumina.emart.entities.CategoryEntity
import lumina.emart.repositories.CategoryRepository
import lumina.emart.utils.fetchRepo
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun createCategory(createCategoryDto: CreateCategoryDto): Response {
        val category = CategoryEntity(
            name = createCategoryDto.name
        )
        categoryRepository.save(category)
        return Response("Category created successfully", null)
    }

    fun updateCategory(updateCategoryDto: UpdateCategoryDto): Response {
        val category = categoryRepository.findById(updateCategoryDto.id).orElse(null) ?: return Response(
            "Category not found",
            null
        )
        updateCategoryDto.name?.let { category.name = it }
        categoryRepository.save(category)
        return Response("Category updated successfully", null)
    }

    fun deleteCategory(id: String): Response {
        categoryRepository.deleteById(id)
        return Response("Category deleted successfully", null)
    }

    fun fetchCategories(page: Int, limit: Int, sortKey: String? = null, sortDirection: String="desc"): Response {
        val data = fetchRepo(categoryRepository, page, limit, sortKey, sortDirection)
        return Response("Categories fetched successfully", data)
    }


}