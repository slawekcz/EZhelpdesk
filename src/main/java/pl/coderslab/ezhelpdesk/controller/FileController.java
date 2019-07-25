package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.ezhelpdesk.entity.File;
import pl.coderslab.ezhelpdesk.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/list")
    public String list(Model model){
        List<File> files = fileService.findAll();
        model.addAttribute("files", files);
        return "file/filelist";
    }

    @PostMapping("/files/list")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "ticketId") Long id, Model model) {
        fileService.saveFile(file, id);
        return "redirect:/ticket/" + id;
    }

    @GetMapping("/ticket/{ticketId}/files/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, @PathVariable(name = "ticketId") Long tId) {
        fileService.delete(id);
        return "redirect:/ticket/" + tId ;
    }

    @GetMapping("/files/download/{id}")
    public String download(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = fileService.findById(id);
        response.setContentType(file.getFileType());
        response.setContentLength(file.getData().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFileName() +"\"");
        FileCopyUtils.copy(file.getData(), response.getOutputStream());
        return "redirect:../list";
    }

}
