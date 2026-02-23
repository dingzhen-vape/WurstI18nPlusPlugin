package com.wursti18nplugin;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WurstI18nPluginClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("wurst_i18n_plus_plugin");
	public final AboutAuthor aboutAuthor = new AboutAuthor();
	@Override
	public void onInitializeClient(){
		LOGGER.info("Wurst I18n Plugin client initialized!");
		
		// 测试资源加载
		testResourceLoading();
	}
	
	/**
	 * 测试资源加载功能
	 */
	private void testResourceLoading() {
		// 在主线程初始化后，资源加载器应该已经可用
		// 这里我们可以测试翻译功能
		LOGGER.info("客户端资源加载测试");
		
		// 注意：资源加载器需要在资源重载事件触发后才能加载翻译
		// 实际使用中，翻译会在资源重载后自动加载
	}
	

	public static String tr(String key) {
		return WurstI18nPlugin.tr(key);
	}
	

	public static String tr(String key, Object... args) {
		return WurstI18nPlugin.tr(key, args);
	}
}