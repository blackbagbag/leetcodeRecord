public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 初始化结果列表，用于存放所有满足条件的四元组
        List<List<Integer>> quadruplets = new ArrayList<>();

        // 判断数组是否为空或长度小于4，如果是，则直接返回空的结果列表
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }

        // 对数组进行排序，方便后续操作
        Arrays.sort(nums);

        // 获取数组长度
        int length = nums.length;

        // 外层循环：遍历数组中的每个元素，除了最后的三个元素
        for (int i = 0; i < length - 3; ++i) {

            // 如果当前元素与前一个元素相同，跳过当前迭代以避免重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 剪枝：如果当前元素与接下来的三个元素的和大于目标值，由于数组已排序，后面的元素只会更大，因此无需进一步搜索，直接结束循环
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }

            // 剪枝：如果当前元素与数组最后三个元素的和仍小于目标值，由于数组已排序，当前元素无法与后面的元素组成满足条件的四元组，因此跳过当前迭代
            if ((long)nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }

            // 内层循环：从外层循环的下一个元素开始，遍历到倒数第二个元素
            for (int j = i + 1; j < length - 2; ++j) {

                // 如果当前元素与前一个元素相同，跳过当前迭代以避免重复
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 剪枝：如果当前元素与接下来的两个元素以及外层循环的元素的和大于目标值，由于数组已排序，后面的元素只会更大，因此无需进一步搜索，直接结束循环
                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }

                // 剪枝：如果当前元素、外层循环的元素以及数组最后两个元素的和仍小于目标值，由于数组已排序，当前元素无法与后面的元素组成满足条件的四元组，因此跳过当前迭```java
                // 迭代
                if ((long)nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }

                // 初始化两个指针，left 指向内层循环的下一个元素，right 指向数组的最后一个元素
                int left = j + 1, right = length - 1;

                // 在 left < right 的条件下，执行以下操作
                while (left < right) {
                    // 计算四个元素的和
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];

                    // 如果四个元素的和等于目标值
                    if (sum == target) {
                        // 添加这四个元素到结果列表
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳过所有与当前 left 位置元素相同的元素
                        while (left < right && nums[left] == nums[left + 1]) {
                            ++left;
                        }

                        // 跳过所有与当前 right 位置元素相同的元素
                        while (left < right && nums[right] == nums[right - 1]) {
                            --right;
                        }

                        // 同时增加 left 和减少 right
                        ++left;
                        --right;
                    } else if (sum < target) {
                        // 如果四个元素的和小于目标值，则增加 left
                        ++left;
                    } else {
                        // 如果四个元素的和大于目标值，则减少 right
                        --right;
                    }
                }
            }
        }

        // 返回结果列表
        return quadruplets;
    }
}
