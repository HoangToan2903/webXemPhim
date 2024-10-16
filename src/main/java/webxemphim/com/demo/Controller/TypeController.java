package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Type;
import webxemphim.com.demo.Service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Controller
@RequestMapping("/type")

public class TypeController {
    @Autowired
    private TypeService typeService;


    @GetMapping("/findAll")
    public String FindAll(Model model, Type type, @PageableDefault(size = 5) Pageable pageable) {
//        List<Type> typeList = typeService.findAll();
        Page<Type> typeList = typeService.findAllPage(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("typeList", typeList);
        model.addAttribute("type", new Type());

        return "admin/ViewType";
    }

    @PostMapping("/save")
    public String SaveNation(Model model,
                             @RequestParam(name = "id") String id,
                             @RequestParam(name = "name") String name,
                             RedirectAttributes ra) {

        try {
            Type type = Type.builder()
                    .id(id)
                    .name(name)
                    .build();

            if (typeService.SaveType(type) instanceof Type) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/type/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewType";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Type type, RedirectAttributes ra) {
        typeService.UpdateType(type, id);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/type/findAll";   // Redirect to the promotion list page after update
    }

    @GetMapping("/delete/{id}")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            typeService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/type/findAll";   // Redirect to the promotion list page after update
    }
}
