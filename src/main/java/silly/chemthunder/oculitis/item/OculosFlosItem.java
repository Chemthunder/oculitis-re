package silly.chemthunder.oculitis.item;

import net.acoyt.acornlib.api.items.CustomHitParticleItem;
import net.acoyt.acornlib.api.items.CustomHitSoundItem;
import net.acoyt.acornlib.api.items.CustomKillSourceItem;
import net.acoyt.acornlib.api.items.KillEffectItem;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import silly.chemthunder.oculitis.cca.OculitisComponent;
import silly.chemthunder.oculitis.indexed.OculitisDamageSources;
import silly.chemthunder.oculitis.ported.ColorableItem;

import java.util.function.Consumer;

@SuppressWarnings("deprecation")
public class OculosFlosItem extends Item implements CustomHitParticleItem, ColorableItem, CustomHitSoundItem, CustomKillSourceItem, KillEffectItem {
    public OculosFlosItem(Settings settings) {
        super(settings);
    }

    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_SCULK_BREAK);
    }

    public DamageSource getKillSource(LivingEntity livingEntity) {
        return OculitisDamageSources.enflowered(livingEntity);
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity entity) {
        spawnHitParticles(playerEntity);
    }

    public int startColor(ItemStack stack) {
        return 0xFF5f3b83;
    }

    public int endColor(ItemStack stack) {
        return 0xFF9b94a3;
    }

    public int backgroundColor(ItemStack stack) {
        return 0xF0120c18;
    }

    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
            AcornParticles.PURPLE_SWEEP, AcornParticles.WHITE_SWEEP
    };

    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        textConsumer.accept(Text.translatable("lore.oculos_1").styled(style -> style.withColor(0xa37dca)));
        textConsumer.accept(Text.translatable("lore.oculos_2").styled(style -> style.withColor(0xa37dca)));

        if (player != null) {
            if (OculitisComponent.KEY.get(player).oculitisForm >= 2) {
                textConsumer.accept(Text.translatable("lore.oculos_unlocked.1").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                textConsumer.accept(Text.translatable("lore.oculos_unlocked.2").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
            }
            if (OculitisComponent.KEY.get(player).oculitisForm >= 3) {
                textConsumer.accept(Text.translatable("lore.oculos_unlocked.2_1").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                textConsumer.accept(Text.translatable("lore.oculos_unlocked.2_2").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                textConsumer.accept(Text.translatable("lore.oculos_unlocked.2_3").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
            }
        }
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }

    // 3a264f
    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        if (!OculitisComponent.KEY.get(user).oculitisState) {
            if (user instanceof PlayerEntity player) {
                OculitisComponent oculitis = OculitisComponent.KEY.get(user);

                oculitis.oculitisState = true;
                oculitis.oculitisForm = 1;
                oculitis.sync();
                player.sendMessage(Text.literal("You feel flowers envelop you").withColor(0x51336e).formatted(Formatting.ITALIC), true);
            }
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        OculitisComponent oculitis = OculitisComponent.KEY.get(user);
        if (user instanceof PlayerEntity player) {

            // debug
            if (player.getOffHandStack().isOf(this)) {
                if (!player.isSneaking()) {
                    if (oculitis.oculitisState) {
                        oculitis.oculitisState = false;
                        oculitis.oculitisForm = 0;
                        oculitis.sync();
                        player.sendMessage(Text.literal("cleansed").withColor(0x51336e), true);
                        player.playSoundToPlayer(SoundEvents.ITEM_LODESTONE_COMPASS_LOCK, SoundCategory.PLAYERS, 1, 1);
                    }
                }
                if (player.isSneaking()) {
                    if (oculitis.oculitisForm >= 1 && oculitis.oculitisForm != 3) {
                        oculitis.oculitisForm++;
                        player.getItemCooldownManager().set(player.getStackInHand(hand), 90);
                        player.sendMessage(Text.literal("-" + oculitis.oculitisForm + "-").withColor(0x51336e), true);
                        player.playSoundToPlayer(SoundEvents.BLOCK_LODESTONE_PLACE, SoundCategory.PLAYERS, 1, 1);
                    }
                }
            }
        }
        return super.use(world, user, hand);
    }
}
