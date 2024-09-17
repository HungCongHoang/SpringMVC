package com.javaweb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
//SecurityContextHolder là một class thuộc Spring Security. 
//Nó cung cấp quyền truy cập vào SecurityContext, nơi lưu trữ thông tin bảo mật hiện tại như người dùng đã xác thực (authentication) và quyền của họ (authorities).
//SecurityContextHolder thường được sử dụng để lấy Authentication object, chứa thông tin về người dùng hiện tại và quyền của họ trong hệ thống.

import com.javaweb.dto.MyUser;

public class SecurityUtils {
	
	//SecurityContextHolder.getContext(): Truy cập vào SecurityContext, nơi lưu trữ thông tin về bảo mật cho phiên làm việc hiện tại.
	//getAuthentication(): Lấy đối tượng Authentication, chứa thông tin về người dùng đã xác thực (đăng nhập).
	//getPrincipal(): Lấy đối tượng Principal, thường là đối tượng đại diện cho người dùng đã đăng nhập.
	//(MyUser): Ép kiểu Principal về kiểu MyUser, một class tuỳ chỉnh đại diện cho người dùng trong hệ thống.
	//return myUser;: Trả về đối tượng MyUser chứa thông tin người dùng hiện tại.
	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
	
	//@SuppressWarnings("unchecked"):
	//Bỏ qua cảnh báo liên quan đến việc ép kiểu không an toàn (unchecked cast). 
	//Cụ thể, trong đoạn code này, có cảnh báo về việc ép kiểu từ Collection<? extends GrantedAuthority> sang List<GrantedAuthority>.
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<>();
		//SecurityContextHolder.getContext().getAuthentication().getAuthorities():
		//Lấy danh sách các quyền (authorities) của người dùng hiện tại từ SecurityContext, nơi lưu trữ thông tin về phiên làm việc bảo mật hiện tại.
		//Ép kiểu List<GrantedAuthority>:
		//Ép kiểu từ danh sách các quyền sang List<GrantedAuthority>. Do kiểu dữ liệu có thể không chính xác, dùng @SuppressWarnings để tránh cảnh báo.
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		//Duyệt qua danh sách các quyền của người dùng.
        for (GrantedAuthority authority : authorities) {
			//Thêm tên của quyền (authority) vào danh sách kết quả (results).
            results.add(authority.getAuthority());
        }
		return results;
	}
}
