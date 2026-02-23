package com.wursti18nplugin.mixin.client;

import com.wursti18nplugin.Translator;
import com.wursti18nplugin.WurstI18nPlugin;
import net.minecraft.world.World;
import net.wurstclient.WurstClient;
import net.wurstclient.WurstTranslator;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.wursti18nplugin.WurstI18nPlugin.getTranslator;
import static com.wursti18nplugin.WurstI18nPlugin.tr;

@Mixin(WurstTranslator.class)
public abstract class InitMixin{

    @Shadow protected abstract ArrayList<String> getCurrentLangCodes();

    @Inject(method = "getCurrentLangCodes",at = @At("RETURN"))
    private void getCurrentLangCodesMixin(CallbackInfoReturnable<ArrayList<String>> cir) {
        // 翻译器已经在WurstI18nPlugin类加载时初始化，直接使用
        WurstI18nPlugin.getTranslator().initialize(cir.getReturnValue().get(1));
        testTranslations();
    }
    
    @Unique
    private void testTranslations() {
        // 翻译器已经初始化，直接使用
        String greeting = tr("wurst_i18n_plus_plugin.greeting");
        LOGGER.info("测试翻译: {}", greeting);

        String loaded = tr("wurst_i18n_plus_plugin.loaded111", "1.0.0");
        LOGGER.info("测试带参数翻译: {}", loaded);

        // 生成缺失翻译
        getTranslator().CreateLackingTranslation();
    }
}