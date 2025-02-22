
def bubble_sort(arr)
  n = arr.length

  # 外层循环，控制比较的轮数
  (0..n - 1).each do |i|
    # 内层循环，逐个比较并交换元素位置
    (0..n - i - 2).each do |j|
      # 如果前一个元素比后一个元素大，则交换它们的位置
      if arr[j] > arr[j + 1]
        arr[j], arr[j + 1] = arr[j + 1], arr[j]
      end
    end
  end

  arr
end

# 示例
unsorted_array = [64, 34, 25, 12, 22, 11, 90]
sorted_array = bubble_sort(unsorted_array.dup)
puts "原始数组：#{unsorted_array}"
puts "排序后数组：#{sorted_array}"
