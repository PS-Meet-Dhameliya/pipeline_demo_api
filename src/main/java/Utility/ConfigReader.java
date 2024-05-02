package Utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigReader {
    ObjectMapper configMapper = new ObjectMapper();
    public Map<String, String> env_details;
    {
        try {
            env_details = (Map<String, String>) configMapper.readValue(Paths.get("TestConfig.json").toFile(), Map.class).get("env");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long get_max_response_time(){
        return Long.parseLong(env_details.get("max_response_time"));
    }

    public String get_api_key(){
        return env_details.get("api-key");
    }

    public String get_base_uri(){
        return env_details.get("base_url");
    }
}
