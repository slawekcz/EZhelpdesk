package pl.coderslab.ezhelpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.ezhelpdesk.entity.File;
import pl.coderslab.ezhelpdesk.repository.FileRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    TicketRepository ticketRepository;

    public File saveFile(MultipartFile multipartFile, Long id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File file = new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            file.setTicket(ticketRepository.findById(id).orElse(null));

            return fileRepository.save(file);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(Long id) throws FileNotFoundException {
        return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found with id " + id));
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public void delete(Long id) {
        fileRepository.deleteById(id);
    }

    public File findById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public List<File> findAllByTicketId(Long id) {
        return fileRepository.findAllByTicketId(id);
    }
}
