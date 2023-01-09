export type OutStorageModel = {
    id: number,
    goodsId: number,
    number: number,
    userId: number,
    remark: string
}

export type OutStorageRecord = {
    id: number,
    goodsId: number,
    number: number,
    userId: number
    goodsName: string,
    userName: string,
    createTime: Date
}