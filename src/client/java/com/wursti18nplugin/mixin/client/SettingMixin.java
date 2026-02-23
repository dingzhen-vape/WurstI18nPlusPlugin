package com.wursti18nplugin.mixin.client;


import com.wursti18nplugin.WurstI18nPlugin;
import net.wurstclient.hack.Hack;
import net.wurstclient.settings.Setting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Setting.class)
public class SettingMixin {

    @Shadow @Final private String name;

    @Inject(method = "getName",at = @At("RETURN"), cancellable = true)
    public void getName(CallbackInfoReturnable<String> cir){
        String OriginName = cir.getReturnValue();
        String NameKey = "I18n.Wurst.Setting.name." + OriginName;
        String TranslatedName = WurstI18nPlugin.tr(NameKey, OriginName);
        if (WurstI18nPlugin.getTranslator() != null) {
            if (!WurstI18nPlugin.getTranslator().hasTranslation(NameKey)) {
                cir.setReturnValue(OriginName);
            }
        }
        cir.setReturnValue(TranslatedName);
    }
}
