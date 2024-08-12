package br.com.pendragon.model.material;


/**
 * Represents the material properties used in a finite element model.
 *
 * <p>This record encapsulates the fundamental properties of a material such as its
 * name, Young's modulus, shear modulus, Poisson's ratio, density, and yield strength.
 * These properties are essential for defining how a material will behave under various
 * loads and conditions in a structural analysis.</p>
 *
 * @param name The name of the material. This is a descriptive label used to identify
 *             the material (e.g., "Steel", "Aluminum").
 * @param E    The Young's modulus (E) of the material, in units of force per unit area
 *             (e.g., Pascals, PSI). It is a measure of the stiffness of the material.
 * @param G    The shear modulus (G) of the material, in units of force per unit area.
 *             It describes the material's response to shear stress.
 * @param ν    The Poisson's ratio (ν) of the material, which is a dimensionless constant
 *             that describes the ratio of lateral strain to axial strain when the material
 *             is subjected to uniaxial stress.
 * @param ρ    The density (ρ) of the material, in units of mass per unit volume
 *             (e.g., kg/m³, lb/ft³). It represents the mass of the material per unit volume.
 * @param fy   The yield strength (fy) of the material, in units of force per unit area.
 *             It is the maximum stress that the material can withstand without permanent
 *             deformation.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Material steel = new Material("Steel", 200e9, 79.3e9, 0.3, 7850.0, 250e6);
 * }</pre>
 *
 * @since Java 17
 */
public record Material(String name,
                       Double E,
                       Double G,
                       Double ν,
                       Double ρ,
                       Double fy) {
}
