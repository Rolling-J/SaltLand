<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./regi_success.css">
    <link rel="stylesheet" href="../bar.css">
	<link rel="stylesheet" href="../footer.css">
    <title>ȸ�� ���� �Ϸ�</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <div class="box_head">
                            <p>Salt Land ���� �Ϸ�</p>
                        </div>
                        <div class="divine"></div>
                        <div class="box_body">
                            <div class="welcome">
                                <p>������ ȯ���մϴ�!</p>
                            </div>
                            <div class="play">    
                                <div class="card_text">
                                    <p>�Բ� ��ܿ�</p>
                                </div>
                                <div class="cards_play">
                                    <div id="play_card">
                                        <p>���̱ⱸ</p>
                                        <div>
                                            <img src="../resources/image/�㸮����.jpg">
                                        </div>
                                    </div>
                                    <div id="play_card">
                                        <p>ü��</p>
                                        <div>
                                            <img src="../resources/image/ü��.jpg">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="btn_box">
                            <p>�α��� �Ϸ� ����</p>
                            <a href="login.jsp">�α���</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>