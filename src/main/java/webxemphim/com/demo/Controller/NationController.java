package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Model.Style;
import webxemphim.com.demo.Service.NationService;

import java.util.List;

@Controller
@RequestMapping("/nation")
public class NationController {

    @Autowired
    private NationService nationService;


    @GetMapping("/findAll")
    public String FindAll(Model model,  @PageableDefault(size = 5) Pageable pageable) {
//        List<Type> typeList = typeService.findAll();
        Page<Nation> nationList = nationService.findAllPage(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("nationList", nationList);
        model.addAttribute("nation", new Nation());

        return "admin/ViewNation";
    }

    @PostMapping("/save")
    public String SaveNation(Model model,
                             @RequestParam(name="id") String id,
                             @RequestParam(name = "name") String name,
                             RedirectAttributes ra){

        try {
            Nation nation = Nation.builder()
                    .id(id)
                    .name(name)
                    .build();

            if (nationService.SaveNation(nation) instanceof Nation) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/nation/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewNation";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Nation nation, RedirectAttributes ra) {
        nationService.UpdateNation(nation, id);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/nation/findAll";   // Redirect to the promotion list page after update
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "[Xóa dữ liệu room]")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            nationService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/nation/findAll";   // Redirect to the promotion list page after update
    }
}
