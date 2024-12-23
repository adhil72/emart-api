package lumina.emart.controllers

import lumina.emart.dtos.CreateSupplierDto
import lumina.emart.dtos.UpdateSupplierDto
import lumina.emart.services.SupplierService
import lumina.emart.utils.FetchParams
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/supplier")
class SupplierController(private val supplierService: SupplierService) {

    @PostMapping("/create")
    fun createSupplier(@RequestBody createSupplierDto: CreateSupplierDto) =
        supplierService.createSupplier(createSupplierDto)

    @GetMapping("/fetch")
    fun fetchSuppliers(@ModelAttribute fetchParams: FetchParams) = supplierService.fetchSuppliers(fetchParams)

    @DeleteMapping("/delete/{id}")
    fun deleteSupplier(@PathVariable id: String) = supplierService.deleteSupplier(id)

    @PutMapping("/update/{id}")
    fun updateSupplier(
        @PathVariable id: String,
        @RequestBody updateSupplierDto: UpdateSupplierDto
    ) = supplierService.updateSupplier(id, updateSupplierDto)


}
