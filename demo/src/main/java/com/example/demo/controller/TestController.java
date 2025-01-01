package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

        @GetMapping("/api/iframe")
        public String iframePage() {
        return """
                <html>
                <head>
                    <title>Iframe Test</title>
                    <script>
                        // Server A의 /set-cookie 호출
                        function setCookieAndLoadIframe() {
                            fetch("https://192.168.0.18:8443/api/set-cookie", { credentials: "include" })
                                .then(response => {
                                    console.log(response)
                                    if (response.ok) {
                                        // 쿠키 설정 후 iframe 로드
                                        document.getElementById("iframeContainer").innerHTML = 
                                            '<iframe src="https://192.168.0.18:8443/api/get-cookie" width="600" height="200" frameborder="1"></iframe>';
                                    } else {
                                        console.error("Failed to set cookie.");
                                    }
                                })
                                .catch(error => console.error("Error calling set-cookie:", error));
                        }

                        // 페이지 로드 후 실행
                        window.onload = setCookieAndLoadIframe;
                    </script>
                </head>
                <body>
                    <h1>Server B</h1>
                    <p>쿠키 설정 후 iframe을 로드합니다.</p>
                    <div id="iframeContainer">Loading iframe...</div>
                </body>
                </html>
                """;
    }
}