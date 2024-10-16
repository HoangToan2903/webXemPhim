package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Model.Style;
import webxemphim.com.demo.Service.NationService;
import webxemphim.com.demo.Service.StyleService;

import java.util.List;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/style")
public class StyleController {
    @Autowired
    private StyleService styleService;


    @GetMapping("/findAll")
    public String FindAll(Model model,  @PageableDefault(size = 5) Pageable pageable) {
//        List<Type> typeList = typeService.findAll();
        Page<Style> styleList = styleService.findAllPage(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("styleList", styleList);
        model.addAttribute("style", new Style());

        return "admin/ViewStyle";
    }

    @PostMapping("/save")
    public String SaveStyle(Model model,
                             @RequestParam(name="id") String id,
                             @RequestParam(name = "name") String name,
                             RedirectAttributes ra){

        try {
            Style style = Style.builder()
                    .id(id)
                    .name(name)
                    .build();

            if (styleService.SaveStyle(style) instanceof Style) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/style/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewStyle";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Style style, RedirectAttributes ra) {
        styleService.UpdateStyle(style, id);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/style/findAll";   // Redirect to the promotion list page after update
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "[Xóa dữ liệu room]")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            styleService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/style/findAll";   // Redirect to the promotion list page after update
    }
}
