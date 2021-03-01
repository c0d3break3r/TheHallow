package pugz.hallows.common.world.noise;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
import net.minecraft.util.SharedSeedRandom;

public class NormalNoise {
    private final double a;
    private final PerlinNoise perlinNoise1;
    private final PerlinNoise perlinNoise2;

    public static NormalNoise a(SharedSeedRandom random, int var1, double... octaves) {
        return new NormalNoise(random, var1, new DoubleArrayList(octaves));
    }

    private NormalNoise(SharedSeedRandom random, int var2, DoubleList octaves) {
        this.perlinNoise1 = PerlinNoise.a(random, var2, octaves);
        this.perlinNoise2 = PerlinNoise.a(random, var2, octaves);
        int var4 = 2147483647;
        int var5 = -2147483648;
        DoubleListIterator iterator = octaves.iterator();

        while(iterator.hasNext()) {
            int var7 = iterator.nextIndex();
            double var8 = iterator.nextDouble();
            if (var8 != 0.0D) {
                var4 = Math.min(var4, var7);
                var5 = Math.max(var5, var7);
            }
        }

        this.a = 0.16666666666666666D / fade(var5 - var4);
    }

    private static double fade(int var0) {
        return 0.1D * (1.0D + 1.0D / (double)(var0 + 1));
    }

    public double a(double x, double y, double z) {
        double var7 = x * 1.0181268882175227D;
        double var9 = y * 1.0181268882175227D;
        double var11 = z * 1.0181268882175227D;
        return (this.perlinNoise1.a(x, y, z) + this.perlinNoise2.a(var7, var9, var11)) * this.a;
    }
}