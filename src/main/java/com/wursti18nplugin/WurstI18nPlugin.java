package com.wursti18nplugin;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WurstI18nPlugin implements ModInitializer {
	public static final String MOD_ID = "wursti18nplugin";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	// 在类加载时就初始化translator，确保Mixin注入时已经可用
	private static final Translator translator = new Translator();
	
	static {
		// 静态初始化块：在类加载时执行
		LOGGER.info("WurstI18nPlugin类正在加载，初始化静态翻译器...");
		// 先使用默认语言初始化，后续Mixin会更新为实际语言
		translator.initialize("zh_cn");
		LOGGER.info("静态翻译器初始化完成");
	}

	@Override
	public void onInitialize() {
		LOGGER.info("I18n for Wurst mod初始化完成！");
		// 翻译器已经在静态块中初始化，这里只需要记录日志
		LOGGER.info("翻译器已就绪，等待Mixin注入语言设置");
	}

	public static Translator getTranslator() {
		return translator;
	}

	public static String tr(String key) {
        return translator.translate(key);
    }

	public static String tr(String key, String name) {
        return translator.translate(key, name);
    }

	public static String tr(String key, Object... args) {
		if (translator != null) {
			return translator.translate(key, args);
		}
		return key;
	}
}