package pugz.hallows.common.world.noise;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.gen.ImprovedNoiseGenerator;

public class PerlinNoise {
    private final ImprovedNoiseGenerator[] improvedNoises;
    private final DoubleList doubles;
    private final double c;
    private final double d;

    public static PerlinNoise a(SharedSeedRandom random, int var1, DoubleList octaves) {
        return new PerlinNoise(random, Pair.of(var1, octaves));
    }

    private PerlinNoise(SharedSeedRandom random, Pair<Integer, DoubleList> var2) {
        int var3 = var2.getFirst();
        this.doubles = var2.getSecond();
        ImprovedNoiseGenerator noise = new ImprovedNoiseGenerator(random);
        int var5 = this.doubles.size();
        int var6 = -var3;
        this.improvedNoises = new ImprovedNoiseGenerator[var5];
        if (var6 >= 0 && var6 < var5) {
            double octave = this.doubles.getDouble(var6);
            if (octave != 0.0D) {
                this.improvedNoises[var6] = noise;
            }
        }

        for(int var13 = var6 - 1; var13 >= 0; --var13) {
            if (var13 < var5) {
                double var8 = this.doubles.getDouble(var13);
                if (var8 != 0.0D) {
                    this.improvedNoises[var13] = new ImprovedNoiseGenerator(random);
                } else {
                    random.skip(262);
                }
            } else {
                random.skip(262);
            }
        }

        if (var6 < var5 - 1) {
            long var14 = (long)(noise.func_215456_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D) * 9.223372036854776E18D);
            SharedSeedRandom random1 = new SharedSeedRandom(var14);

            for(int var10 = var6 + 1; var10 < var5; ++var10) {
                if (var10 >= 0) {
                    double var11 = this.doubles.getDouble(var10);
                    if (var11 != 0.0D) {
                        this.improvedNoises[var10] = new ImprovedNoiseGenerator(random1);
                    } else {
                        random1.skip(262);
                    }
                } else {
                    random1.skip(262);
                }
            }
        }

        this.d = Math.pow(2.0D, -var6);
        this.c = Math.pow(2.0D, var5 - 1) / (Math.pow(2.0D, var5) - 1.0D);
    }

    public double a(double x, double y, double z) {
        return this.a(x, y, z, 0.0D, 0.0D, false);
    }

    public double a(double x, double y, double z, double var7, double var9, boolean var11) {
        double var12 = 0.0D;
        double var14 = this.d;
        double var16 = this.c;

        for(int var18 = 0; var18 < this.improvedNoises.length; ++var18) {
            ImprovedNoiseGenerator noise = this.improvedNoises[var18];
            if (noise != null) {
                var12 += this.doubles.getDouble(var18) * noise.func_215456_a(a(x * var14), var11 ? -noise.yCoord : a(y * var14), a(z * var14), var7 * var14, var9 * var14) * var16;
            }

            var14 *= 2.0D;
            var16 /= 2.0D;
        }

        return var12;
    }

    public static double a(double var0) {
        return var0 - Math.floor(var0 / 3.3554432E7D + 0.5D) * 3.3554432E7D;
    }
}