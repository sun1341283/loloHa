package cn.offcn.service.archives;

import cn.offcn.entity.Employee;
import cn.offcn.utils.OAResult;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivesService {

    public Employee getEmployeeArchivesByEid(Integer eid);

    public OAResult getAndSaveArchives(MultipartFile files);
}
