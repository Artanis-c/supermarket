export type GoodsModel = {
    id: number,
    goodsName: string,
    price: number,
    stock: number,
    remark: string,
    createTime: Date
}

export type GoodsStatisticModel = {
    goodsName: string,
    inNum: number,
    outNum: number
}