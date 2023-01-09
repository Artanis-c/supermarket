import { ActionType, DrawerForm, PageContainer, ProColumns, ProFormDigit, ProFormInstance, ProFormSelect, ProFormText, ProFormTextArea, ProTable } from '@ant-design/pro-components';
import { Button } from 'antd';
import React, { useRef, useState } from 'react';
import { goodsKeyValue } from '../Goods/GoodsService';
import { InStorageModel, InStorageRecord } from './InStorageModel';
import { addInStorage, getInStorage, inStorageList } from './InStorageService';

const InStorage: React.FC = () => {
    const ref = useRef<ActionType>();
    const formRef = useRef<ProFormInstance>();
    const viewFormRef = useRef<ProFormInstance>();
    const [formVisible, setFromVisible] = useState<boolean>(false);
    const [viewVisble, setViewVisble] = useState<boolean>(false);

    const columns: ProColumns<InStorageRecord>[] = [
        {
            dataIndex: 'id',
            valueType: 'indexBorder',
            width: 48,
        },
        {
            title: '商品名称',
            dataIndex: 'goodsName',
        },
        {
            title: '入库数量',
            dataIndex: 'number',
            hideInSearch: true
        },
        {
            title: '操作人',
            dataIndex: 'userName',
        },
        {
            title: '入库时间',
            dataIndex: 'createTime',
            hideInSearch: true
        },
        {
            title: '备注',
            dataIndex: 'remark',
            hideInSearch: true
        },
        {
            title: '操作',
            valueType: 'option',
            render: (text, record, _, action) => [
                <a key='edit' onClick={async () => {
                    const res = await getInStorage(record.id)
                    console.log(res.data)
                    viewFormRef.current?.setFieldsValue(res.data)
                    setViewVisble(true)
                }}>查看</a>
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
                        const res = await inStorageList(params)
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
                            商品入库
                        </Button>,
                    ]
                }}
            />
            <DrawerForm<InStorageModel>
                width="md"
                formRef={formRef}
                visible={formVisible}
                onVisibleChange={(x) => setFromVisible(x)}
                onFinish={async (x) => {
                    addInStorage(x)
                    setFromVisible(false);
                    ref.current?.reload();
                    formRef.current?.resetFields();
                }}
            >
                <ProFormSelect
                    rules={[{ required: true }]}
                    name='goodsId'
                    label='入库商品'
                    request={async () => {
                        const res = await goodsKeyValue();
                        return res.data;
                    }} />
                <ProFormDigit name='number' label='入库数量' min={1} max={1000} rules={[{ required: true }]} />
                <ProFormTextArea name='remark' label='备注' />
            </DrawerForm>

            <DrawerForm<InStorageModel>
                visible={viewVisble}
                formRef={viewFormRef}
                onVisibleChange={(x) => setViewVisble(x)}
            >
                <ProFormText name='goodsName' label='商品名称' readonly={true} />
                <ProFormText name='number' label='入库数量' readonly={true} />
                <ProFormText name='userName' label='操作人' readonly={true} />
                <ProFormTextArea name='remark' label='备注' readonly={true} />
            </DrawerForm>
        </PageContainer>
    )
}

export default InStorage