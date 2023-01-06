import { ActionType, DrawerForm, PageContainer, ProColumns, ProFormDigit, ProFormInstance, ProFormText, ProFormTextArea, ProTable } from '@ant-design/pro-components';
import { Button } from 'antd';
import React, { useRef, useState } from 'react';
import { GoodsModel } from './GoodsModel';
import { addGoods, editGoods, getGoods, goodsList } from './GoodsService';

const Goods: React.FC = () => {
    const ref = useRef<ActionType>();
    const formRef = useRef<ProFormInstance>();
    const [formVisible, setFromVisible] = useState<boolean>(false);
    const [isEdit, setisEdit] = useState<boolean>(false);
    const [goodsId, setGoodsId] = useState<number>(0);
    const columns: ProColumns<GoodsModel>[] = [
        {
            dataIndex: 'index',
            valueType: 'indexBorder',
            width: 48,
        },
        {
            title: '商品名称',
            dataIndex: 'goodsName',
        },
        {
            title: '商品价格',
            dataIndex: 'price',
            hideInSearch: true
        },
        {
            title: '商品库存',
            dataIndex: 'stock',
            hideInSearch: true
        },
        {
            title: '备注',
            dataIndex: 'remark',
            hideInSearch: true
        },
        {
            title: '录入时间',
            dataIndex: 'createTime',
            valueType: 'dateTime',
            hideInSearch: true
        },
        {
            title: '操作',
            valueType: 'option',
            render: (text, record, _, action) => [
                <a key='edit' onClick={async () => {
                    setisEdit(true);
                    const res = await getGoods(record.id)
                    formRef.current?.setFieldsValue(res.data)
                    setGoodsId(record.id)
                    setFromVisible(true)
                }}>编辑/查看</a>
            ]
        }
    ]

    return (
        <PageContainer>
            <ProTable columns={columns}
                actionRef={ref}
                rowKey="id"
                request={
                    async (params, sort, filter) => {
                        const res = await goodsList(params)
                        return {
                            data: res.data.records,
                            total: res.data.total,
                            success: true
                        }
                    }
                }
                toolbar={{
                    actions: [
                        <Button key="primary" type="primary" onClick={() => { setFromVisible(true) }}>
                            添加商品
                        </Button>,
                    ]
                }}
            />
            <DrawerForm<GoodsModel>
                formRef={formRef}
                visible={formVisible}
                onVisibleChange={(x) => setFromVisible(x)}
                onFinish={async (x) => {
                    if (isEdit) {
                        x.id = goodsId
                        editGoods(x)
                    } else {
                        addGoods(x)
                    }
                    setFromVisible(false);
                    setisEdit(false);
                    formRef.current?.resetFields();
                    formRef.current?.resetFields();
                    ref.current?.reload();
                }}
            >
                <ProFormText name='goodsName' label='商品名称' rules={[{ required: true }]} />
                <ProFormDigit name='price' label='商品价格' rules={[{ required: true }]} />
                <ProFormDigit name='stock' label='商品库存' rules={[{ required: true }]} />
                <ProFormTextArea name='remark' label='备注' />
            </DrawerForm>
        </PageContainer>
    )
}
export default Goods;