package ljdp.minechem.client.sound;

import ljdp.minechem.common.ModMinechem;
import ljdp.minechem.common.utils.ConstantValue;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class MinechemSoundEvent {

    @ForgeSubscribe
    public void onSound(SoundLoadEvent event) {
        event.manager.soundPoolSounds.addSound(ConstantValue.TEXTURE_MOD_ID+"assets/minechem/sound/minechem/projector.ogg");
    }

}
