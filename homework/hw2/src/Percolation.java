import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int gridSize;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf1;
    private final boolean[] openSites;
    private int numberOfOpenSites=0;
    private final int virtualTop;
    private final int virtualBottom;

    public Percolation(int N) {
        if (N<=0)throw new IllegalArgumentException("只能是非负数");
        int numberOfElements=N*N+2;//需要额外的两个位置来装虚拟顶和底
        gridSize = N;
        uf = new WeightedQuickUnionUF(numberOfElements);//uf是用来算percolates的，uf1是用来算union的，如此设置是为了处理uf的反流，就是计算union的时候通过bottom与top connect的情况（还有一种设计方式好像是给每个结点两个状态，就是需要一个top与一个bottom的数组来对每个节点记录他们是否与top和bottom相连）
        uf1=new WeightedQuickUnionUF(numberOfElements-1);
        openSites=new boolean[gridSize*gridSize];

        virtualTop=gridSize*gridSize;//设置为比原来的N方-1的后一项
        virtualBottom=gridSize*gridSize+1;

        for(int t=0;t<gridSize;t++){uf.union(t,virtualTop);}
        for(int t=0;t<gridSize;t++){uf1.union(t,virtualTop);}//只有上节点
        for(int t=0;t<gridSize;t++){
            uf.union(xyTo1D(gridSize-1,t),virtualBottom);}
    }

    public int xyTo1D(int r, int c){
        return r*gridSize+c;
    }

    public void open(int row, int col) {
        if (row>=gridSize || col>=gridSize||row<0 || col<0){
            throw new IndexOutOfBoundsException("太大或者太小");
        }
        if(!openSites[xyTo1D(row, col)]){
            numberOfOpenSites++;
            openSites[xyTo1D(row,col)]=true;
            if (row-1>=0&&isOpen(row-1,col)){ //这个if句的左右必须是这个顺序，否则会报错
                uf.union(xyTo1D(row-1,col),xyTo1D(row,col));
                uf1.union(xyTo1D(row-1,col),xyTo1D(row,col));
            }
            if (row+1<=gridSize-1&&isOpen(row+1,col)){
                uf.union(xyTo1D(row+1,col),xyTo1D(row,col));
                uf1.union(xyTo1D(row+1,col),xyTo1D(row,col));
            }
            if (col-1>=0&&isOpen(row,col-1)){
                uf.union(xyTo1D(row,col-1),xyTo1D(row,col));
                uf1.union(xyTo1D(row,col-1),xyTo1D(row,col));
            }
            if (col+1<=gridSize-1&&isOpen(row,col+1)){
                uf.union(xyTo1D(row,col+1),xyTo1D(row,col));
                uf1.union(xyTo1D(row,col+1),xyTo1D(row,col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row>=gridSize || col>=gridSize||row<0 || col<0) {
            throw new IndexOutOfBoundsException("太大或者太小");
        }
        else return openSites[xyTo1D(row,col)];
    }

    public boolean isFull(int row, int col) {
        if (row>=gridSize || col>=gridSize||row<0 || col<0){
            throw new IndexOutOfBoundsException("太大或者太小");
        }
        return isOpen(row,col)&&uf1.connected(virtualTop,xyTo1D(row,col));
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return uf.connected(virtualTop,virtualBottom);
    }

}
