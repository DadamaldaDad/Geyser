/*
 * Copyright (c) 2019-2021 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.network.translators.java.entity.spawn;

import com.github.steveice10.mc.protocol.packet.ingame.clientbound.entity.spawn.ClientboundAddPaintingPacket;
import com.nukkitx.math.vector.Vector3f;
import org.geysermc.geyser.entity.PaintingEntity;
import org.geysermc.geyser.network.session.GeyserSession;
import org.geysermc.geyser.network.translators.PacketTranslator;
import org.geysermc.geyser.network.translators.Translator;
import org.geysermc.geyser.utils.PaintingType;

@Translator(packet = ClientboundAddPaintingPacket.class)
public class JavaAddPaintingTranslator extends PacketTranslator<ClientboundAddPaintingPacket> {

    @Override
    public void translate(GeyserSession session, ClientboundAddPaintingPacket packet) {
        Vector3f position = Vector3f.from(packet.getPosition().getX(), packet.getPosition().getY(), packet.getPosition().getZ());

        PaintingEntity entity = new PaintingEntity(session, packet.getEntityId(),
                session.getEntityCache().getNextEntityId().incrementAndGet(), packet.getUuid(),
                position, PaintingType.getByPaintingType(packet.getPaintingType()), packet.getDirection().getHorizontalIndex());

        session.getEntityCache().spawnEntity(entity);
    }
}