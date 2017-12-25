% 8.
% a. Write a predicate to determine if a list has even numbers of elements 
% without counting the elements from the list.
% b. Write a predicate to delete first occurrence of the minimum number from a list.

% a

% Model matematic:
% even_list(l1...ln) =
% 	true, n = 0
% 	false, n = 1
% 	even_list(l3...ln), otherwise
 
% even_list(L:list)
% even_list(i)

even_list([]).
even_list([_, _|T]) :- even_list(T).

% b

% Model matematic:
% min_numbers(a, b) =
% 	a, a <= b
% 	b, a > b

% min_numbers(A:number, B:number, R:number)
% min_numbers(i, i, o)

min_numbers(A, B, A) :- A =< B.
min_numbers(A, B, B) :- A > B.

% Model matematic:
% minim_list(l1...ln) =
% 	l1, n = 1
% 	min_numbers(l1, minim_list(l2...ln)), otherwise
	
% minim_list(L:list, R:number)
% minim_list(i, o)

minim_list([H], H).
minim_list([H|T], R) :- 
    minim_list(T, R1),
    min_numbers(H, R1, R).

% Model matematic:
% delete_first_occurence(l1...ln, m) =
% 	l2...ln, l1 = m
% 	l1 + delete_first_occurence(l2...ln, m), otherwise

% delete_first_occurence(L:list, M:number, R:list)
% delete_first_occurence(i, i, o)

delete_first_occurence([H|T], H, T) :- !.
delete_first_occurence([H|T], M, [H|R]) :- delete_first_occurence(T, M, R).

% delete_first_min(L:list, R:list)
% delete_first_min(i, o)

delete_first_min(L, R) :-
    minim_list(L, RM),
    delete_first_occurence(L, RM, R).