package com.wursti18nplugin;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.wurstclient.WurstClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Translator {
    public static final Logger LOGGER = LoggerFactory.getLogger("WurstI18nPlugin/ResourceLoader");
    private static final String MOD_ID = WurstI18nPlugin.MOD_ID;
    
    private final Map<String, String> translations = new HashMap<>();
    private final Map<String,String> LackingTranslation = new HashMap<>();
    
    /**
     * 初始化资源加载器
     */
    public void initialize(String langCode) {
        loadTranslations(langCode);
        // Mixinz注入同时获取langcode
    }
    
    /**
     * 加载翻译文件
     */
    private void loadTranslations(String langCode) {
        translations.clear();

        if (!loadTranslationFile(langCode)) {
            // 尝试加载英文作为后备
            loadTranslationFile("en_us");
        }
        
        LOGGER.info("已加载 {} 条翻译", translations.size());
    }
    
    /**
     * 加载指定语言的翻译文件
     */
    private boolean loadTranslationFile(String langCode) {
        String filePath = "/assets/" + MOD_ID + "/translations/" + langCode + ".json";
        
        try (InputStream stream = getClass().getResourceAsStream(filePath)) {
            if (stream != null) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    for (String key : json.keySet()) {
                        translations.put(key, json.get(key).getAsString());
                    }
                    
                    LOGGER.info("成功加载 {} 翻译文件，包含 {} 条翻译", langCode, json.size());
                    return true;
                }
            } else {
                LOGGER.warn("未找到翻译文件: {}", filePath);
            }
        } catch (Exception e) {
            LOGGER.error("加载翻译文件时出错: {}", filePath, e);
        }
        
        return false;
    }


    public void CreateLackingTranslation(){
        LOGGER.info("缺少翻译数量: {}",this.LackingTranslation.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(LackingTranslation);
        java.io.File directory = new java.io.File("lackingTranslations");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try(FileWriter writer = new FileWriter("lackingTranslations/LackingTranslation.json")){
            writer.write(json);
        } catch (Exception e) {
            LOGGER.error("写入缺少翻译文件时出错", e);
        }
    }


    /**
     * 获取翻译
     */
    public String translate(String key) {
        if (!hasTranslation(key)){
            LOGGER.warn("缺少翻译: {}", key);
            LackingTranslation.put(key,key);
        }
        return translations.getOrDefault(key, key);
    }

    public String translate(String key,String name) {
        if (!hasTranslation(key)){
            LOGGER.warn("缺少翻译: {},{}", key,name);
            LackingTranslation.put(key,name);
        }
        return translations.getOrDefault(key, key);
    }
    
    /**
     * 获取翻译，支持参数替换
     */
    public String translate(String key, Object... args) {
        String template = translations.getOrDefault(key, key);
        try {
            return String.format(template, args);
        } catch (Exception e) {
            LOGGER.error("格式化翻译时出错: key={}, template={}", key, template, e);
            return template;
        }
    }
    
    /**
     * 检查是否有指定键的翻译
     */
    public boolean hasTranslation(String key) {
        return translations.containsKey(key);
    }
    
    /**
     * 获取所有翻译
     */
    public Map<String, String> getAllTranslations() {
        return new HashMap<>(translations);
    }
    
    /**
     * 从类路径读取任意文件
     */
    public Optional<String> readResourceFile(String filePath) {
        String fullPath = "/assets/" + MOD_ID + "/" + filePath;
        
        try (InputStream stream = getClass().getResourceAsStream(fullPath)) {
            if (stream != null) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    
                    return Optional.of(content.toString().trim());
                }
            }
        } catch (Exception e) {
            LOGGER.error("读取资源文件时出错: {}", fullPath, e);
        }
        
        return Optional.empty();
    }
    
    /**
     * 从类路径读取 JSON 文件
     */
    public Optional<JsonObject> readJsonResource(String filePath) {
        String fullPath = "/assets/" + MOD_ID + "/" + filePath;
        
        try (InputStream stream = getClass().getResourceAsStream(fullPath)) {
            if (stream != null) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    return Optional.of(json);
                }
            }
        } catch (Exception e) {
            LOGGER.error("读取 JSON 资源文件时出错: {}", fullPath, e);
        }
        
        return Optional.empty();
    }
}