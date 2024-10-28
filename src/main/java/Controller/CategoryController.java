package Controller;

import model.Category;
import service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String search,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        Page<Category> categories = service.listCategories(search, PageRequest.of(page, 5));
        model.addAttribute("categories", categories);
        model.addAttribute("search", search);
        return "categories/list";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categories/addOrEdit";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category) {
        service.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", service.getCategoryById(id));
        return "categories/addOrEdit";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return "redirect:/categories";
    }
}