package com.minecode.test;

import java.util.Scanner;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/7
 * @desc：本题知识积累欠缺，附一份检索出的资料。
 */
public class GuideTest {
        public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入");
            while (scanner.hasNext()){
                int N=scanner.nextInt();//N个节点
                int M=scanner.nextInt();//M条路径
                int[] tmp=new int[N+1];//每个节点的权重
                for(int i=1;i<=N;i++)
                    tmp[i]=scanner.nextInt();
                int[][] weights=new int[N+1][N+1];
                //初始化无穷大
                for(int i=1;i<=N;i++){
                    for(int j=1;j<=N;j++)
                        weights[i][j]=Integer.MAX_VALUE;
                }

                for(int i=0;i<M;i++){
                    int from=scanner.nextInt();
                    int to=scanner.nextInt();
                    //因是无向图,所以权重同时有2个被赋值
                    weights[from][to]=tmp[to];//这里只是记录了从a到b时候,b的权重,作为边的权重.所以最后整条路径上,自然找了起点的权重.故最后输出结果必须加上起点的权重.
                    weights[to][from]=tmp[from];
                }
                System.out.println(dijkstra(weights,1)+tmp[1]);
            }
        }

        /**
         * 单源最短路径
         * @param weights 权重矩阵
         * @param start 开始节点编号
         * @return
         */
        private static int dijkstra(int[][] weights,int start){
            int n=weights.length;//顶点个数+1
            int[] dist=new int[n]; //保存:从start出发,到其他各个顶点的最短路径
            boolean[] isVisited=new boolean[n];//记录节点的最短路径是否已经求出,true表示已经求出
            //初始化第一个节点
            dist[1]=0;
            isVisited[1]=true;

            int k;
            for(int i=2;i<n;i++){ //需要访问所有节点
                k=-1;
                int minValue=Integer.MAX_VALUE;
                for(int j=1;j<n;j++){ //查找一个距离start最近的节点
                    if(!isVisited[j] && weights[start][j]<minValue){
                        k=j;
                        minValue=weights[start][j];
                    }
                }
                /**注意:如果是有向图，可能出现情况:就是n个节点并没有遍历完,但是已经找不到:节点既未被遍历同时,由当前已经遍历的节点能到达这些未 被遍历的点.说明这些点,是无法到达的
                 * 当然了,本题是无向图,这种情况是不会出现的。
                 */
                if(k==-1)
                    break;
                //修改节点k的值
                dist[k]=minValue;
                isVisited[k]=true;

                //修改其他未访问节点,在经过中间节点k后,新的最短路径
                for(int j=1;j<n;j++){
                    if(!isVisited[j] &&  weights[k][j]!=Integer.MAX_VALUE &&  weights[start][k]+weights[k][j]<weights[start][j] ){
                        weights[start][j]=weights[start][k]+weights[k][j];
                    }
                }
            }
            return weights[start][n-1];
        }
    }
