package com.dpslink.schmidt.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.dpslink.schmidt.business.FileManipulationHandler;
import com.dpslink.schmidt.models.DirectoryPaths;

@Controller
public class PageController {

	@GetMapping(value="/") 
	public String landingPage(Model model) {
		model.addAttribute("directoryPaths", new DirectoryPaths());
		return "index.html";
	}
	
    @PostMapping("/")
    public String submitDirectoryPaths(@ModelAttribute DirectoryPaths directoryPaths) throws IOException {
    	FileManipulationHandler schmidtFileManipulationHandler = new FileManipulationHandler(directoryPaths);
		ArrayList<File> directoryFiles = schmidtFileManipulationHandler.retrieveFiles();
		
		schmidtFileManipulationHandler.copyFiles(directoryFiles);

        return "result";
    }
}
