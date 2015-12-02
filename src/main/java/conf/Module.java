package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import services.InitialDataService;

/**
 * @author kawasima
 */
@Singleton
public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(InitialDataService.class);
    }
}
