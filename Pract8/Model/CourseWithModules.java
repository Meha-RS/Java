package Pract8.Model;

import java.util.List;

public record CourseWithModules(
    String code, 
    List<Module> modules
) {}