package crw.lock.cloudlock.service.impl;

import crw.lock.cloudlock.service.LockService;
import org.springframework.stereotype.Service;

@Service
public class LockServiceImpl implements LockService {

    @Override
    public void synchorizedDemo() {
        synchronized (this) {
            int i = 0;
            i++;
        }
    }
}
