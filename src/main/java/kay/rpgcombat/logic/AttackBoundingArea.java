package kay.rpgcombat.logic;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.List;

public class AttackBoundingArea {


    private EntityPlayer myPlayer;
    private AreaType myAreaType;
    private AxisAlignedBB aabb;

    private double originX;
    private double originY;
    private double originZ;
    private double theta;

    private double x1;
    private double x2;
    private double z1;
    private double z2;

    public AttackBoundingArea(EntityPlayer player, AreaType areaType, double r, @Nullable double a, double hA, double hB, double d) {
        myPlayer = player;
        myAreaType = areaType;

        originX = myPlayer.posX;
        originY = myPlayer.posY;
        originZ = myPlayer.posZ;
        theta = myPlayer.cameraYaw / 360 * 2 * Math.PI;
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        if (myAreaType == AreaType.CONE || myAreaType == AreaType.RECTANGLE) {
            if (myAreaType == AreaType.CONE)
                a = 2 * r * Math.sin(a / 360 * Math.PI);

            x1 = - a / 2 * cos - d * sin;
            x2 = - a / 2 * sin + d * cos;
            z1 = a / 2 * cos - (r + d) * sin;
            z2 = a / 2 * sin + (r + d) * cos;
        }
        else {
            if (myAreaType == AreaType.CIRCLE) {
                x1 = - r - d * sin;
                z1 = - r + d * cos;
                x2 = r - d * sin;
                z2 = r + d * cos;
            }
            else {
                x1 = 0;
                x2 = 0;
                z1 = 0;
                z2 = 0;
            }
        }

        aabb = new AxisAlignedBB(originX + x1, originY - hB, originZ + z1, originX + x2, originY + hA, originZ + z2);
    }

    public <T extends Entity> List<T> getEntitiesWithin(Class <? extends T > clazz) {
        return getEntitiesWithin(clazz, EntitySelectors.NOT_SPECTATING);
    }
    public <T extends Entity> List<T> getEntitiesWithin(Class <? extends T > clazz, @Nullable Predicate<? super T > filter) {
        return getMyPlayer().getEntityWorld().getEntitiesWithinAABB(clazz, aabb, filter);
    }

    public EntityPlayer getMyPlayer() {
      return myPlayer;
    }

    public AreaType getMyAreaType() {
        return myAreaType;
    }
}
