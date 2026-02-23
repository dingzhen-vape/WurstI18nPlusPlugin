package com.wursti18nplugin.mixin.client;


import com.wursti18nplugin.WurstI18nPlugin;
import net.wurstclient.hack.Hack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Hack.class)
public class HackMixin {

    @Inject(method = "getName",at = @At("RETURN"), cancellable = true)
    public void getName(CallbackInfoReturnable<String> cir){
        String OriginName = cir.getReturnValue();
        String NameKey = "I18n.Wurst.Hack.name." + OriginName;
        String TranslatedName = WurstI18nPlugin.tr(NameKey, OriginName);
        
        // 添加 null 检查，避免空指针异常
        if (WurstI18nPlugin.getTranslator() != null) {
            if (!WurstI18nPlugin.getTranslator().hasTranslation(NameKey)) {
                cir.setReturnValue(OriginName);
            }
        }
        cir.setReturnValue(TranslatedName);
    }
}
