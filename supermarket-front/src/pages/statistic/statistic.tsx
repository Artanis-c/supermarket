import { ActionType, DrawerForm, PageContainer, ProColumns, ProFormDigit, ProFormInstance, ProFormSelect, ProFormText, ProFormTextArea, ProTable } from '@ant-design/pro-components';
import { GoodsStatisticModel } from '../Goods/GoodsModel';
import { goodsStatistic } from '../Goods/GoodsService';

const Statstic: React.FC = () => {

    const columns: ProColumns<GoodsStatisticModel>[] = [
        {
            title: '商品名称',
            dataIndex: 'goodsName',
            hideInSearch: true
        },
        {
            title: '入库数量',
            dataIndex: 'inNum',
            hideInSearch: true
        },
        {
            title: '出库数量',
            dataIndex: 'outNum',
            hideInSearch: true
        },
    ]

    return (
        <ProTable columns={columns}
            request={
                async (params, sort, filter) => {
                    const res = await goodsStatistic()
                    return {
                        data: res.data,
                        success: true
                    }
                }
            }
        >

        </ProTable>
    )
}
export default Statstic