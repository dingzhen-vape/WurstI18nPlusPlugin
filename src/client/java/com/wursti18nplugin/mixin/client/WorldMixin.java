package com.wursti18nplugin.mixin.client;

import com.wursti18nplugin.AboutAuthor;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import net.wurstclient.hack.HackList;
import net.wurstclient.util.ChatUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class WorldMixin {
    @Inject(method = "onFinishedLoading", at = @At("RETURN"))
    private void onFinishedLoading(CallbackInfo ci) {
        ChatUtils.message("""
                本插件完全免费且开源，若你是付费获取，请举报卖家并退款
                插件作者：食我压路
                BiliBili主页：https://space.bilibili.com/432060575
                github主页：https://github.com/dingzhen-vape/WurstI18nPlusPlugin
                """);
    }
}
