package mx.tecmilenio.citas.service;

import mx.tecmilenio.citas.domain.Admin;
import mx.tecmilenio.citas.persistence.AdminRepositoryCsv;
import mx.tecmilenio.citas.persistence.CsvFileManager;
import mx.tecmilenio.citas.util.PasswordUtil;

public class AuthService {
    private final AdminRepositoryCsv adminRepo;
    private final CsvFileManager fm;
    private final String adminsFile;

    public AuthService(AdminRepositoryCsv adminRepo, CsvFileManager fm, String adminsFile) {
        this.adminRepo = adminRepo;
        this.fm = fm;
        this.adminsFile = adminsFile;
        bootstrapDefaultAdminIfNeeded();
    }

    private void bootstrapDefaultAdminIfNeeded() {
        try {
            if (adminRepo.isEmptyOrHeaderOnly()) {
                String hash = PasswordUtil.sha256("admin123");
                fm.appendLine(adminsFile, "admin," + hash);
                System.out.println("[INFO] No se encontraron admins. Se creó admin por defecto (admin/admin123).");
            }
        } catch (Exception e) {
            System.out.println("[WARN] No fue posible crear admin por defecto: " + e.getMessage());
        }
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
