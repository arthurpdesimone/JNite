package br.com.pendragon.model.elements;

import br.com.pendragon.model.geometry.Node3D;
import br.com.pendragon.model.loads.LoadCombo;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a 3D spring element in a finite element model.
 */
public class Spring3D {

    private static final double EPSILON = 1e-10;

    private String name;       // A unique name for the spring given by the user
    private Integer ID;        // Unique index number for the spring assigned by the program
    private Node3D iNode;        // The spring's i-node
    private Node3D jNode;        // The spring's j-node
    private double ks;         // The spring constant (force/displacement)
    private Map<String, LoadCombo> loadCombos; // The dictionary of load combinations in the model this spring belongs to
    private boolean tensionOnly; // Indicates whether the spring is tension-only
    private boolean compOnly;    // Indicates whether the spring is compression-only

    // Springs need to track whether they are active or not for any given load combination.
    // They may become inactive for a load combination during a tension/compression-only
    // analysis. This dictionary will be used when the model is solved.
    private Map<String, Boolean> active; // Key = load combo name, Value = True or False

    /**
     * Constructor for Spring3D.
     *
     * @param name The unique name for the spring.
     * @param iNode The i-node of the spring.
     * @param jNode The j-node of the spring.
     * @param ks The spring constant.
     * @param loadCombos The dictionary of load combinations.
     * @param tensionOnly Indicates if the spring is tension-only.
     * @param compOnly Indicates if the spring is compression-only.
     */
    public Spring3D(String name, Node3D iNode, Node3D jNode, double ks,
                    Map<String, LoadCombo> loadCombos, boolean tensionOnly, boolean compOnly) {
        this.name = name;
        this.iNode = iNode;
        this.jNode = jNode;
        this.ks = ks;
        this.loadCombos = loadCombos;
        this.tensionOnly = tensionOnly;
        this.compOnly = compOnly;
        this.active = new HashMap<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Node3D getINode() {
        return iNode;
    }

    public void setINode(Node3D iNode) {
        this.iNode = iNode;
    }

    public Node3D getJNode() {
        return jNode;
    }

    public void setJNode(Node3D jNode) {
        this.jNode = jNode;
    }

    public double getKs() {
        return ks;
    }

    public void setKs(double ks) {
        this.ks = ks;
    }

    public Map<String, LoadCombo> getLoadCombos() {
        return loadCombos;
    }

    public void setLoadCombos(Map<String, LoadCombo> loadCombos) {
        this.loadCombos = loadCombos;
    }

    public boolean isTensionOnly() {
        return tensionOnly;
    }

    public void setTensionOnly(boolean tensionOnly) {
        this.tensionOnly = tensionOnly;
    }

    public boolean isCompOnly() {
        return compOnly;
    }

    public void setCompOnly(boolean compOnly) {
        this.compOnly = compOnly;
    }

    public Map<String, Boolean> getActive() {
        return active;
    }

    public void setActive(Map<String, Boolean> active) {
        this.active = active;
    }

    /**
     * Returns the length of the spring.
     *
     * @return The length of the spring.
     */
    public double getLength() {
        return iNode.distance(jNode);
    }

    /**
     * Returns the local stiffness matrix for the spring.
     *
     * @return The local stiffness matrix.
     */
    public RealMatrix getLocalStiffnessMatrix() {
        double ks = this.ks;

        double[][] k = {
                {ks,  0, 0, 0, 0, 0, -ks, 0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {-ks, 0, 0, 0, 0, 0, ks,  0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0},
                {0,   0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0}
        };

        return MatrixUtils.createRealMatrix(k);
    }

    /**
     * Returns the spring's local end force vector for the given load combination.
     *
     * @param comboName The name of the load combination.
     * @return The local end force vector.
     */
    public RealMatrix getLocalEndForceVector(String comboName) {
        return getLocalStiffnessMatrix().multiply(getLocalDisplacementVector(comboName));
    }

    /**
     * Returns the spring's local displacement vector for the given load combination.
     *
     * @param comboName The name of the load combination.
     * @return The local displacement vector.
     */
    public RealMatrix getLocalDisplacementVector(String comboName) {
        return getTransformationMatrix().transpose().multiply(getGlobalDisplacementVector(comboName));
    }

    /**
     * Returns the transformation matrix for the spring.
     *
     * @return The transformation matrix.
     */
    public RealMatrix getTransformationMatrix() {
        Vector3D x1 = new Vector3D(iNode.getX(), iNode.getY(), iNode.getZ());
        Vector3D x2 = new Vector3D(jNode.getX(), jNode.getY(), jNode.getZ());
        double L = getLength();

        // Calculate the direction cosines for the local x-axis
        Vector3D x = x2.subtract(x1).normalize();

        Vector3D y;
        Vector3D z;
        // Vertical springs
        if (Math.abs(x1.getX() - x2.getX()) < EPSILON && Math.abs(x1.getZ() - x2.getZ()) < EPSILON) {
            y = (x2.getY() > x1.getY()) ? new Vector3D(-1, 0, 0) : new Vector3D(1, 0, 0);
            z = new Vector3D(0, 0, 1);
        }
        // Horizontal springs
        else if (Math.abs(x1.getY() - x2.getY()) < EPSILON) {
            y = new Vector3D(0, 1, 0);
            z = x.crossProduct(y).normalize();
        }
        // Members neither vertical nor horizontal
        else {
            Vector3D proj = new Vector3D(x2.getX() - x1.getX(), 0, x2.getZ() - x1.getZ());
            z = (x2.getY() > x1.getY()) ? proj.crossProduct(x) : x.crossProduct(proj);
            z = z.normalize();
            y = z.crossProduct(x).normalize();
        }

        // Create the direction cosines matrix
        RealMatrix dirCos = MatrixUtils.createRealMatrix(new double[][] {
                {x.getX(), x.getY(), x.getZ()},
                {y.getX(), y.getY(), y.getZ()},
                {z.getX(), z.getY(), z.getZ()}
        });

        // Build the transformation matrix
        RealMatrix transMatrix = MatrixUtils.createRealMatrix(12, 12);
        transMatrix.setSubMatrix(dirCos.getData(), 0, 0);
        transMatrix.setSubMatrix(dirCos.getData(), 3, 3);
        transMatrix.setSubMatrix(dirCos.getData(), 6, 6);
        transMatrix.setSubMatrix(dirCos.getData(), 9, 9);

        return transMatrix;
    }

    /**
     * Returns the spring's global stiffness matrix.
     *
     * @return The global stiffness matrix.
     */
    public RealMatrix getGlobalStiffnessMatrix() {
        RealMatrix transMatrix = getTransformationMatrix();
        RealMatrix invTransMatrix = new LUDecomposition(transMatrix).getSolver().getInverse();
        return invTransMatrix.multiply(getLocalStiffnessMatrix()).multiply(transMatrix);
    }

    /**
     * Returns the spring's global end force vector for the given load combination.
     *
     * @param comboName The name of the load combination.
     * @return The global end force vector.
     */
    public RealMatrix getGlobalEndForceVector(String comboName) {
        RealMatrix transMatrix = getTransformationMatrix();
        RealMatrix invTransMatrix = new LUDecomposition(transMatrix).getSolver().getInverse();
        return invTransMatrix.multiply(getLocalEndForceVector(comboName));
    }

    /**
     * Returns the spring's global displacement vector for the given load combination.
     *
     * @param comboName The name of the load combination.
     * @return The global displacement vector.
     */
    public RealMatrix getGlobalDisplacementVector(String comboName) {
        RealMatrix D = MatrixUtils.createRealMatrix(12, 1);

        if (active.getOrDefault(comboName, false)) {
            D.setEntry(0, 0, iNode.getDX().get(comboName));
            D.setEntry(6, 0, jNode.getDX().get(comboName));
        }

        D.setEntry(1, 0, iNode.getDisplacementY(comboName));
        D.setEntry(2, 0, iNode.getDisplacementZ(comboName));
        D.setEntry(3, 0, iNode.getRotationX(comboName));
        D.setEntry(4, 0, iNode.getRotationY(comboName));
        D.setEntry(5, 0, iNode.getRotationZ(comboName));
        D.setEntry(7, 0, jNode.getDisplacementY(comboName));
        D.setEntry(8, 0, jNode.getDisplacementZ(comboName));
        D.setEntry(9, 0, jNode.getRotationX(comboName));
        D.setEntry(10, 0, jNode.getRotationY(comboName));
        D.setEntry(11, 0, jNode.getRotationZ(comboName));

        return D;
    }

    /**
     * Returns the axial force in the spring for the given load combination.
     *
     * @param comboName The name of the load combination.
     * @return The axial force.
     */
    public double getAxialForce(String comboName) {
        return getLocalEndForceVector(comboName).getEntry(0, 0);
    }
}

