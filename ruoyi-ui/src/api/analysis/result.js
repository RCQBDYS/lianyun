import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listResult(query) {
  return request({
    url: '/system/result/list',
    method: 'get',
    params: query
  })
}

// 下载【log分析之后的结果】 excel
export function downloadResult(scriptId) {
  return request({
    url: '/analysis/result/download/'+scriptId,
    method:'get',
    responseType:'blob'
  })
}

// 查询【根据scriptId进行result】结果集
export function getResultByScriptId(scriptId) {
  return request({
    url: '/analysis/result/getByScriptId/' + scriptId,
    method:'get'
  })

}

// 查询【根据scriptId查询log分析结果】详细
export function getResult(resultId) {
  return request({
    url: '/analysis/result/' + resultId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addResult(data) {
  return request({
    url: '/system/result',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateResult(data) {
  return request({
    url: '/system/result',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delResult(resultId) {
  return request({
    url: '/system/result/' + resultId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportResult(query) {
  return request({
    url: '/system/result/export',
    method: 'get',
    params: query
  })
}
