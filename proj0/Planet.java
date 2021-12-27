public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //与给定行星间的距离
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.hypot(dx, dy);
    }

    //给定行星施加的力
    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double r = calcDistance(p);
        double force = G * this.mass * p.mass / (r * r);

        return force;
    }

    //返回x方向的力
    public double calcForceExertedByX(Planet p) {
        double Fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
        return Fx;
    }

    //返回x方向的力
    public double calcForceExertedByY(Planet p) {
        double Fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
        return Fy;
    }

    /**
     * Calcualte the net force in x direction and y direction
     * 多个星体在x和y方向的力的总和
     * 思路：列一个数组，把所有星体在X方向（y方向）的力加起来
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double FxNet = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                //自己不能给自己施加重力
                FxNet += this.calcForceExertedByX(p);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double FyNet = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                //自己不能给自己施加重力
                FyNet += this.calcForceExertedByY(p);
            }
        }
        return FyNet;
    }

    public void update(double dt, double fx, double fy) {
        double Ax = fx / this.mass;
        double Ay = fy / this.mass;
        this.xxVel += Ax * dt;
        this.yyVel += Ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;//这些属性都是更新的
    }

    /**
     * Draw the picture of the Body according to its position
     * 用draw.picture方法
     */
    public void draw() {

        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }



}
