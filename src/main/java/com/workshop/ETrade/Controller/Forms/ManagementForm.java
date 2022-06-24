package com.workshop.ETrade.Controller.Forms;

import java.util.List;
import java.util.Map;

public class ManagementForm {
    public List<String> owners;
    public Map<String, String> managers;

    public ManagementForm(List<String> owners, Map<String, String> managers) {
        this.owners = owners;
        this.managers = managers;
    }
}
