package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Director;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Service.CastService;
import webxemphim.com.demo.Service.NationService;
import webxemphim.com.demo.Util.UploadImage;

import java.util.List;
@RequestMapping("/cast")
@Controller
public class CastController {

    @Autowired
    private CastService castService;

    @Autowired
    private UploadImage uploadImage;

    @GetMapping("/findAll")
    public String FindAll(Model model, Cast nation){
        List<Cast> castList = castService.findAll();
        model.addAttribute("castList",castList);
        model.addAttribute("cast", new Cast());

        return "admin/ViewCast";
    }

    @PostMapping("/save")
    public String SaveNation(Model model,
                             @RequestParam(name="id") String id,
                             @RequestParam(name = "name") String name,
                             RedirectAttributes ra){

        try {
            Cast cast = Cast.builder()
                    .id(id)
                    .name(name)
                    .build();

            if (castService.SaveCast(cast) instanceof Cast) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/cast/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewCast";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Cast cast, RedirectAttributes ra) {
        castService.UpdateCast(cast, id);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/cast/findAll";   // Redirect to the promotion list page after update
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "[Xóa dữ liệu room]")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            castService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/cast/findAll";   // Redirect to the promotion list page after update
    }
}
