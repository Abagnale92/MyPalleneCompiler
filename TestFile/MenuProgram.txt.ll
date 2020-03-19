; ModuleID = 'MenuProgram.txt.c'
source_filename = "MenuProgram.txt.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@continu = local_unnamed_addr global i32 1, align 4
@.str = private unnamed_addr constant [33 x i8] c"Scegli l'operazione da eseguire \00", align 1
@.str.1 = private unnamed_addr constant [11 x i8] c"1 - Somma \00", align 1
@.str.2 = private unnamed_addr constant [21 x i8] c"2 - Moltiplicazione \00", align 1
@.str.3 = private unnamed_addr constant [15 x i8] c"3 - Divisione \00", align 1
@.str.4 = private unnamed_addr constant [26 x i8] c"4 - Elevamento a potenza \00", align 1
@.str.5 = private unnamed_addr constant [30 x i8] c"5 - Successione di Fibonacci \00", align 1
@.str.6 = private unnamed_addr constant [19 x i8] c"0 - Esci dal Menu'\00", align 1
@.str.7 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.8 = private unnamed_addr constant [13 x i8] c"Inserisci a:\00", align 1
@.str.9 = private unnamed_addr constant [13 x i8] c"Inserisci b:\00", align 1
@.str.10 = private unnamed_addr constant [12 x i8] c"Inserisci b\00", align 1
@.str.11 = private unnamed_addr constant [12 x i8] c"Inserisci a\00", align 1
@array = common local_unnamed_addr global [50 x i32] zeroinitializer, align 16
@arr = common local_unnamed_addr global [50 x i32] zeroinitializer, align 16

; Function Attrs: nounwind uwtable
define i32 @Menu() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %2) #5
  %3 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([33 x i8], [33 x i8]* @.str, i64 0, i64 0))
  %4 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([11 x i8], [11 x i8]* @.str.1, i64 0, i64 0))
  %5 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str.2, i64 0, i64 0))
  %6 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([15 x i8], [15 x i8]* @.str.3, i64 0, i64 0))
  %7 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([26 x i8], [26 x i8]* @.str.4, i64 0, i64 0))
  %8 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([30 x i8], [30 x i8]* @.str.5, i64 0, i64 0))
  %9 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([19 x i8], [19 x i8]* @.str.6, i64 0, i64 0))
  %10 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %1)
  %11 = load i32, i32* %1, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %2) #5
  ret i32 %11
}

; Function Attrs: argmemonly nounwind
declare void @llvm.lifetime.start.p0i8(i64, i8* nocapture) #1

; Function Attrs: nounwind
declare i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: nounwind
declare i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: argmemonly nounwind
declare void @llvm.lifetime.end.p0i8(i64, i8* nocapture) #1

; Function Attrs: nounwind uwtable
define i32 @Somma() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %3) #5
  %4 = bitcast i32* %2 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %4) #5
  %5 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0))
  %6 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %1)
  %7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.9, i64 0, i64 0))
  %8 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %2)
  %9 = load i32, i32* %1, align 4, !tbaa !2
  %10 = load i32, i32* %2, align 4, !tbaa !2
  %11 = add nsw i32 %10, %9
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %4) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %3) #5
  ret i32 %11
}

; Function Attrs: nounwind uwtable
define i32 @Moltiplicazione() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %3) #5
  %4 = bitcast i32* %2 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %4) #5
  %5 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0))
  %6 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %1)
  %7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.9, i64 0, i64 0))
  %8 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %2)
  %9 = load i32, i32* %2, align 4, !tbaa !2
  %10 = icmp slt i32 %9, 1
  %11 = load i32, i32* %1, align 4
  %12 = mul i32 %11, %9
  %13 = select i1 %10, i32 0, i32 %12
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %4) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %3) #5
  ret i32 %13
}

; Function Attrs: norecurse nounwind readnone uwtable
define i32 @Divisione(i32, i32) local_unnamed_addr #3 {
  %3 = icmp slt i32 %0, %1
  br i1 %3, label %11, label %4

; <label>:4:                                      ; preds = %2
  br label %5

; <label>:5:                                      ; preds = %4, %5
  %6 = phi i32 [ %9, %5 ], [ 0, %4 ]
  %7 = phi i32 [ %8, %5 ], [ %0, %4 ]
  %8 = sub nsw i32 %7, %1
  %9 = add nuw nsw i32 %6, 1
  %10 = icmp slt i32 %8, %1
  br i1 %10, label %11, label %5

; <label>:11:                                     ; preds = %5, %2
  %12 = phi i32 [ 0, %2 ], [ %9, %5 ]
  ret i32 %12
}

; Function Attrs: nounwind uwtable
define i32 @Potenza() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %3) #5
  %4 = bitcast i32* %2 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %4) #5
  %5 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0))
  %6 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %1)
  %7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str.10, i64 0, i64 0))
  %8 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %2)
  %9 = load i32, i32* %2, align 4, !tbaa !2
  %10 = icmp slt i32 %9, 1
  br i1 %10, label %84, label %11

; <label>:11:                                     ; preds = %0
  %12 = load i32, i32* %1, align 4, !tbaa !2
  %13 = icmp ult i32 %9, 8
  br i1 %13, label %75, label %14

; <label>:14:                                     ; preds = %11
  %15 = and i32 %9, -8
  %16 = or i32 %15, 1
  %17 = insertelement <4 x i32> undef, i32 %12, i32 0
  %18 = shufflevector <4 x i32> %17, <4 x i32> undef, <4 x i32> zeroinitializer
  %19 = insertelement <4 x i32> undef, i32 %12, i32 0
  %20 = shufflevector <4 x i32> %19, <4 x i32> undef, <4 x i32> zeroinitializer
  %21 = add i32 %15, -8
  %22 = lshr exact i32 %21, 3
  %23 = add nuw nsw i32 %22, 1
  %24 = and i32 %23, 7
  %25 = icmp ult i32 %21, 56
  br i1 %25, label %50, label %26

; <label>:26:                                     ; preds = %14
  %27 = sub nsw i32 %23, %24
  br label %28

; <label>:28:                                     ; preds = %28, %26
  %29 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %26 ], [ %46, %28 ]
  %30 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %26 ], [ %47, %28 ]
  %31 = phi i32 [ %27, %26 ], [ %48, %28 ]
  %32 = mul nsw <4 x i32> %18, %29
  %33 = mul nsw <4 x i32> %20, %30
  %34 = mul nsw <4 x i32> %18, %32
  %35 = mul nsw <4 x i32> %20, %33
  %36 = mul nsw <4 x i32> %18, %34
  %37 = mul nsw <4 x i32> %20, %35
  %38 = mul nsw <4 x i32> %18, %36
  %39 = mul nsw <4 x i32> %20, %37
  %40 = mul nsw <4 x i32> %18, %38
  %41 = mul nsw <4 x i32> %20, %39
  %42 = mul nsw <4 x i32> %18, %40
  %43 = mul nsw <4 x i32> %20, %41
  %44 = mul nsw <4 x i32> %18, %42
  %45 = mul nsw <4 x i32> %20, %43
  %46 = mul nsw <4 x i32> %18, %44
  %47 = mul nsw <4 x i32> %20, %45
  %48 = add i32 %31, -8
  %49 = icmp eq i32 %48, 0
  br i1 %49, label %50, label %28, !llvm.loop !6

; <label>:50:                                     ; preds = %28, %14
  %51 = phi <4 x i32> [ undef, %14 ], [ %46, %28 ]
  %52 = phi <4 x i32> [ undef, %14 ], [ %47, %28 ]
  %53 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %14 ], [ %46, %28 ]
  %54 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %14 ], [ %47, %28 ]
  %55 = icmp eq i32 %24, 0
  br i1 %55, label %65, label %56

; <label>:56:                                     ; preds = %50
  br label %57

; <label>:57:                                     ; preds = %57, %56
  %58 = phi <4 x i32> [ %53, %56 ], [ %61, %57 ]
  %59 = phi <4 x i32> [ %54, %56 ], [ %62, %57 ]
  %60 = phi i32 [ %24, %56 ], [ %63, %57 ]
  %61 = mul nsw <4 x i32> %18, %58
  %62 = mul nsw <4 x i32> %20, %59
  %63 = add i32 %60, -1
  %64 = icmp eq i32 %63, 0
  br i1 %64, label %65, label %57, !llvm.loop !8

; <label>:65:                                     ; preds = %57, %50
  %66 = phi <4 x i32> [ %51, %50 ], [ %61, %57 ]
  %67 = phi <4 x i32> [ %52, %50 ], [ %62, %57 ]
  %68 = mul <4 x i32> %67, %66
  %69 = shufflevector <4 x i32> %68, <4 x i32> undef, <4 x i32> <i32 2, i32 3, i32 undef, i32 undef>
  %70 = mul <4 x i32> %68, %69
  %71 = shufflevector <4 x i32> %70, <4 x i32> undef, <4 x i32> <i32 1, i32 undef, i32 undef, i32 undef>
  %72 = mul <4 x i32> %70, %71
  %73 = extractelement <4 x i32> %72, i32 0
  %74 = icmp eq i32 %9, %15
  br i1 %74, label %84, label %75

; <label>:75:                                     ; preds = %65, %11
  %76 = phi i32 [ 1, %11 ], [ %16, %65 ]
  %77 = phi i32 [ 1, %11 ], [ %73, %65 ]
  br label %78

; <label>:78:                                     ; preds = %75, %78
  %79 = phi i32 [ %82, %78 ], [ %76, %75 ]
  %80 = phi i32 [ %81, %78 ], [ %77, %75 ]
  %81 = mul nsw i32 %12, %80
  %82 = add nuw nsw i32 %79, 1
  %83 = icmp slt i32 %79, %9
  br i1 %83, label %78, label %84, !llvm.loop !10

; <label>:84:                                     ; preds = %78, %65, %0
  %85 = phi i32 [ 1, %0 ], [ %73, %65 ], [ %81, %78 ]
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %4) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %3) #5
  ret i32 %85
}

; Function Attrs: nounwind readnone uwtable
define i32 @Fibonacci(i32) local_unnamed_addr #4 {
  switch i32 %0, label %3 [
    i32 0, label %9
    i32 1, label %2
  ]

; <label>:2:                                      ; preds = %1
  br label %9

; <label>:3:                                      ; preds = %1
  %4 = add nsw i32 %0, -1
  %5 = tail call i32 @Fibonacci(i32 %4)
  %6 = add nsw i32 %0, -2
  %7 = tail call i32 @Fibonacci(i32 %6)
  %8 = add nsw i32 %7, %5
  ret i32 %8

; <label>:9:                                      ; preds = %1, %2
  %10 = phi i32 [ 1, %2 ], [ %0, %1 ]
  ret i32 %10
}

; Function Attrs: nounwind uwtable
define i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i32, align 4
  %9 = alloca i32, align 4
  %10 = bitcast i32* %8 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %10) #5
  %11 = bitcast i32* %9 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %11) #5
  %12 = load i32, i32* @continu, align 4, !tbaa !2
  %13 = icmp eq i32 %12, 1
  br i1 %13, label %14, label %163

; <label>:14:                                     ; preds = %0
  %15 = bitcast i32* %7 to i8*
  %16 = bitcast i32* %5 to i8*
  %17 = bitcast i32* %6 to i8*
  %18 = bitcast i32* %3 to i8*
  %19 = bitcast i32* %4 to i8*
  %20 = bitcast i32* %1 to i8*
  %21 = bitcast i32* %2 to i8*
  br label %22

; <label>:22:                                     ; preds = %14, %160
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %15) #5
  %23 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([33 x i8], [33 x i8]* @.str, i64 0, i64 0)) #5
  %24 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([11 x i8], [11 x i8]* @.str.1, i64 0, i64 0)) #5
  %25 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str.2, i64 0, i64 0)) #5
  %26 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([15 x i8], [15 x i8]* @.str.3, i64 0, i64 0)) #5
  %27 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([26 x i8], [26 x i8]* @.str.4, i64 0, i64 0)) #5
  %28 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([30 x i8], [30 x i8]* @.str.5, i64 0, i64 0)) #5
  %29 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([19 x i8], [19 x i8]* @.str.6, i64 0, i64 0)) #5
  %30 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %7) #5
  %31 = load i32, i32* %7, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %15) #5
  switch i32 %31, label %160 [
    i32 1, label %32
    i32 2, label %41
    i32 3, label %52
    i32 4, label %70
    i32 5, label %153
    i32 0, label %159
  ]

; <label>:32:                                     ; preds = %22
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %16) #5
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %17) #5
  %33 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0)) #5
  %34 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %5) #5
  %35 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.9, i64 0, i64 0)) #5
  %36 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %6) #5
  %37 = load i32, i32* %5, align 4, !tbaa !2
  %38 = load i32, i32* %6, align 4, !tbaa !2
  %39 = add nsw i32 %38, %37
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %17) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %16) #5
  %40 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32 %39)
  br label %160

; <label>:41:                                     ; preds = %22
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %18) #5
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %19) #5
  %42 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0)) #5
  %43 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %3) #5
  %44 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.9, i64 0, i64 0)) #5
  %45 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %4) #5
  %46 = load i32, i32* %4, align 4, !tbaa !2
  %47 = icmp slt i32 %46, 1
  %48 = load i32, i32* %3, align 4
  %49 = mul i32 %48, %46
  %50 = select i1 %47, i32 0, i32 %49
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %19) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %18) #5
  %51 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32 %50)
  br label %160

; <label>:52:                                     ; preds = %22
  %53 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str.11, i64 0, i64 0))
  %54 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %8)
  %55 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str.10, i64 0, i64 0))
  %56 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %9)
  %57 = load i32, i32* %8, align 4, !tbaa !2
  %58 = load i32, i32* %9, align 4, !tbaa !2
  %59 = icmp slt i32 %57, %58
  br i1 %59, label %67, label %60

; <label>:60:                                     ; preds = %52
  br label %61

; <label>:61:                                     ; preds = %60, %61
  %62 = phi i32 [ %65, %61 ], [ 0, %60 ]
  %63 = phi i32 [ %64, %61 ], [ %57, %60 ]
  %64 = sub nsw i32 %63, %58
  %65 = add nuw nsw i32 %62, 1
  %66 = icmp slt i32 %64, %58
  br i1 %66, label %67, label %61

; <label>:67:                                     ; preds = %61, %52
  %68 = phi i32 [ 0, %52 ], [ %65, %61 ]
  %69 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32 %68)
  br label %160

; <label>:70:                                     ; preds = %22
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %20) #5
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %21) #5
  %71 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0)) #5
  %72 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %1) #5
  %73 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str.10, i64 0, i64 0)) #5
  %74 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %2) #5
  %75 = load i32, i32* %2, align 4, !tbaa !2
  %76 = icmp slt i32 %75, 1
  br i1 %76, label %150, label %77

; <label>:77:                                     ; preds = %70
  %78 = load i32, i32* %1, align 4, !tbaa !2
  %79 = icmp ult i32 %75, 8
  br i1 %79, label %141, label %80

; <label>:80:                                     ; preds = %77
  %81 = and i32 %75, -8
  %82 = or i32 %81, 1
  %83 = insertelement <4 x i32> undef, i32 %78, i32 0
  %84 = shufflevector <4 x i32> %83, <4 x i32> undef, <4 x i32> zeroinitializer
  %85 = insertelement <4 x i32> undef, i32 %78, i32 0
  %86 = shufflevector <4 x i32> %85, <4 x i32> undef, <4 x i32> zeroinitializer
  %87 = add i32 %81, -8
  %88 = lshr exact i32 %87, 3
  %89 = add nuw nsw i32 %88, 1
  %90 = and i32 %89, 7
  %91 = icmp ult i32 %87, 56
  br i1 %91, label %116, label %92

; <label>:92:                                     ; preds = %80
  %93 = sub nsw i32 %89, %90
  br label %94

; <label>:94:                                     ; preds = %94, %92
  %95 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %92 ], [ %112, %94 ]
  %96 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %92 ], [ %113, %94 ]
  %97 = phi i32 [ %93, %92 ], [ %114, %94 ]
  %98 = mul nsw <4 x i32> %95, %84
  %99 = mul nsw <4 x i32> %96, %86
  %100 = mul nsw <4 x i32> %98, %84
  %101 = mul nsw <4 x i32> %99, %86
  %102 = mul nsw <4 x i32> %100, %84
  %103 = mul nsw <4 x i32> %101, %86
  %104 = mul nsw <4 x i32> %102, %84
  %105 = mul nsw <4 x i32> %103, %86
  %106 = mul nsw <4 x i32> %104, %84
  %107 = mul nsw <4 x i32> %105, %86
  %108 = mul nsw <4 x i32> %106, %84
  %109 = mul nsw <4 x i32> %107, %86
  %110 = mul nsw <4 x i32> %108, %84
  %111 = mul nsw <4 x i32> %109, %86
  %112 = mul nsw <4 x i32> %110, %84
  %113 = mul nsw <4 x i32> %111, %86
  %114 = add i32 %97, -8
  %115 = icmp eq i32 %114, 0
  br i1 %115, label %116, label %94, !llvm.loop !12

; <label>:116:                                    ; preds = %94, %80
  %117 = phi <4 x i32> [ undef, %80 ], [ %112, %94 ]
  %118 = phi <4 x i32> [ undef, %80 ], [ %113, %94 ]
  %119 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %80 ], [ %112, %94 ]
  %120 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %80 ], [ %113, %94 ]
  %121 = icmp eq i32 %90, 0
  br i1 %121, label %131, label %122

; <label>:122:                                    ; preds = %116
  br label %123

; <label>:123:                                    ; preds = %123, %122
  %124 = phi <4 x i32> [ %119, %122 ], [ %127, %123 ]
  %125 = phi <4 x i32> [ %120, %122 ], [ %128, %123 ]
  %126 = phi i32 [ %90, %122 ], [ %129, %123 ]
  %127 = mul nsw <4 x i32> %124, %84
  %128 = mul nsw <4 x i32> %125, %86
  %129 = add i32 %126, -1
  %130 = icmp eq i32 %129, 0
  br i1 %130, label %131, label %123, !llvm.loop !13

; <label>:131:                                    ; preds = %123, %116
  %132 = phi <4 x i32> [ %117, %116 ], [ %127, %123 ]
  %133 = phi <4 x i32> [ %118, %116 ], [ %128, %123 ]
  %134 = mul <4 x i32> %133, %132
  %135 = shufflevector <4 x i32> %134, <4 x i32> undef, <4 x i32> <i32 2, i32 3, i32 undef, i32 undef>
  %136 = mul <4 x i32> %134, %135
  %137 = shufflevector <4 x i32> %136, <4 x i32> undef, <4 x i32> <i32 1, i32 undef, i32 undef, i32 undef>
  %138 = mul <4 x i32> %136, %137
  %139 = extractelement <4 x i32> %138, i32 0
  %140 = icmp eq i32 %75, %81
  br i1 %140, label %150, label %141

; <label>:141:                                    ; preds = %131, %77
  %142 = phi i32 [ 1, %77 ], [ %82, %131 ]
  %143 = phi i32 [ 1, %77 ], [ %139, %131 ]
  br label %144

; <label>:144:                                    ; preds = %141, %144
  %145 = phi i32 [ %148, %144 ], [ %142, %141 ]
  %146 = phi i32 [ %147, %144 ], [ %143, %141 ]
  %147 = mul nsw i32 %146, %78
  %148 = add nuw nsw i32 %145, 1
  %149 = icmp eq i32 %145, %75
  br i1 %149, label %150, label %144, !llvm.loop !14

; <label>:150:                                    ; preds = %144, %131, %70
  %151 = phi i32 [ 1, %70 ], [ %139, %131 ], [ %147, %144 ]
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %21) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %20) #5
  %152 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32 %151)
  br label %160

; <label>:153:                                    ; preds = %22
  %154 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.8, i64 0, i64 0))
  %155 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32* nonnull %8)
  %156 = load i32, i32* %8, align 4, !tbaa !2
  %157 = call i32 @Fibonacci(i32 %156)
  %158 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), i32 %157)
  br label %160

; <label>:159:                                    ; preds = %22
  store i32 0, i32* @continu, align 4, !tbaa !2
  br label %163

; <label>:160:                                    ; preds = %153, %150, %67, %41, %32, %22
  %161 = load i32, i32* @continu, align 4, !tbaa !2
  %162 = icmp eq i32 %161, 1
  br i1 %162, label %22, label %163

; <label>:163:                                    ; preds = %160, %159, %0
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %11) #5
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %10) #5
  ret i32 0
}

attributes #0 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { argmemonly nounwind }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { norecurse nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 6.0.0-1ubuntu2 (tags/RELEASE_600/final)"}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.isvectorized", i32 1}
!8 = distinct !{!8, !9}
!9 = !{!"llvm.loop.unroll.disable"}
!10 = distinct !{!10, !11, !7}
!11 = !{!"llvm.loop.unroll.runtime.disable"}
!12 = distinct !{!12, !7}
!13 = distinct !{!13, !9}
!14 = distinct !{!14, !11, !7}
