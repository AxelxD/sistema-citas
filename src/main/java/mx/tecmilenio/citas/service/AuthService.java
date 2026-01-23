package mx.tecmilenio.citas.service;

import mx.tecmilenio.citas.domain.Admin;
import mx.tecmilenio.citas.persistence.AdminRepositoryCsv;
import mx.tecmilenio.citas.util.PasswordUtil;

public class AuthService {
    private final AdminRepositoryCsv adminRepo;

    public AuthService(AdminRepositoryCsv adminRepo) {
        this.adminRepo = adminRepo;
    }

    public boolean login(String id, String password) throws Exception {
        String hash = PasswordUtil.sha256(password);
        for (Admin a : adminRepo.findAll()) {
            if (a.getId().equalsIgnoreCase(id) && a.getPasswordHash().equalsIgnoreCase(hash)) {
                return true;
            }
        }
        return false;
    }
}
