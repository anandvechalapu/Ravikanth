
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

// Controller class

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping
    public ResponseEntity<Object> configureJiraAndGithub(@RequestBody ConfigurationRequest request) {
        configurationService.configureJiraAndGithub(request);
        return ResponseEntity.ok().build();
    }
}

// Service class

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public void configureJiraAndGithub(ConfigurationRequest request) {
        // validate the credentials
        if (validateCredentials(request)) {
            // save the credentials in the repository
            configurationRepository.save(request);
            // redirect to the service home page
            redirectToServiceHomePage(request);
        } else {
            // show an error message
            showErrorMessage();
        }
    }

    private boolean validateCredentials(ConfigurationRequest request) {
        // code to validate the credentials
        return true;
    }

    private void redirectToServiceHomePage(ConfigurationRequest request) {
        // code to redirect to the service home page
    }

    private void showErrorMessage() {
        // code to show an error message
    }
}

// Repository class

@Repository
public class ConfigurationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConfigurationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ConfigurationRequest request) {
        String sql = "INSERT INTO configuration (jira_credentials, github_credentials) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getJiraCredentials(), request.getGithubCredentials());
    }
}